variable "aws_region" {
  type    = string
  default = "us-east-1"
}
variable "source_ami" {
  type    = string
  default = "ami-08c40ec9ead489470" # Ubuntu 22.04 LTS
}

variable "ami_regions" {
  type = list(string)
  default = [
    "us-east-1",
  ]
}


variable "ssh_username" {
  type    = string
  default = "ubuntu"
}

variable "subnet_id" {
  type    = string
  default = "subnet-0a47a4d173aaaea61"
}

variable "aws_access_key_id" {
  type    = string
  default = env("AWS_ACCESS_KEY_ID")
}

variable "aws_secret_access_key" {
  type    = string
  default = env("AWS_SECRET_ACCESS_KEY")
}

variable "aws_profile" {
  type    = string
  default = env("AWS_PROFILE")
}

variable "ami_user" {
  type    = list(string)
  default = ["958333861027",]
}



source "amazon-ebs" "my-ami" {
  region                  = "${var.aws_region}"
  ami_name                = "csye6225_${formatdate("YYYY_MM_DD_hh_mm_ss", timestamp())}"
  ami_description         = "AMI for CSYE 6225"
  ami_users               = "${var.ami_user}"
  profile                 = "${var.aws_profile}"
  access_key              = "${var.aws_access_key_id}"
  secret_key              = "${var.aws_secret_access_key}"
  ssh_agent_auth          = false
  temporary_key_pair_type = "ed25519"
  ami_regions             = "${var.ami_regions}"

  aws_polling {
    delay_seconds = 120
    max_attempts  = 50
  }

  instance_type = "t2.micro"
  source_ami    = "${var.source_ami}"
  ssh_username  = "${var.ssh_username}"
  subnet_id     = "${var.subnet_id}"

  launch_block_device_mappings {
    delete_on_termination = true
    device_name           = "/dev/sda1"
    volume_size           = 8
    volume_type           = "gp2"
  }
}

build {
  sources = ["source.amazon-ebs.my-ami"]

  provisioner "file" {
    source      = "../target/webapp-0.0.1-SNAPSHOT.jar"
    destination = "/home/ubuntu/"
  }
  provisioner "file" {
    source      = "cloudwatch-config.json"
    destination = "/home/ubuntu/"
  }
  provisioner "file" {
    source      = "reboot.sh"
    destination = "/home/ubuntu/"
  }

  provisioner "shell" {
    inline = [
      "sudo touch /home/ubuntu/application.properties",
      "sudo chmod 764 /home/ubuntu/application.properties",
    ]
  }

  provisioner "shell" {
    inline = [
      "sudo mv /home/ubuntu/reboot.sh /var/lib/cloud/scripts/per-boot/",
      "sudo chmod 777 /var/lib/cloud/scripts/per-boot/reboot.sh"
    ]
  }

  provisioner "shell" {
    script       = "setup.sh"
    pause_before = "10s"
    timeout      = "10s"
  }

  post-processor "manifest" {
    output = "manifest.json"
    strip_path = true
  }

}
