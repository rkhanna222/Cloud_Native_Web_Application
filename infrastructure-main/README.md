# Infrastructure as a code using Cloud Formation

# AWS CLI

## AWS Profile

-  aws configure --profile=dev

-  aws configure --profile=prod

- export AWS_PROFILE=dev

- export AWS_PROFILE=prod

- With the AWS Command Line Interface (AWS CLI), you can create, monitor, update, and delete stacks from your system's terminal. You can also use the AWS CLI to automate actions through scripts. For more information about the AWS CLI, see the AWS Command Line Interface User Guide.

# CREATE A STACK

- To create a stack you run the aws cloudformation create-stack command. You must provide the stack name, the location of a valid template, and any input parameters.

aws cloudformation --profile admin-prod --region us-east-1 create-stack \
  --stack-name myteststack \
  --template-body file://csye6225-infra.yaml \
--parameters ParameterKey=AmiID,ParameterValue="ami-0d2f2edc1b681cd42"

- aws cloudformation create-stack \
  --stack-name myteststack \
  --template-body file://csye6225-infra.yaml \
  --parameters ParameterKey=Parm1,ParameterValue=test1 ParameterKey=Parm2,ParameterValue=test2

- {
  "StackId" : "arn:aws:cloudformation:us-east-1:123456789012:stack/myteststack/330b0120-1771-11e4-af37-50ba1b98bea6"
}

# DELETE A STACK

- aws cloudformation delete-stack \
  --stack-name myteststack 

# UPDATE A STACK

- aws cloudformation update-stack --stack-name myvpc --template-body file://csye6225-infra.yaml


## AWS Networking

aws ec2 create-vpc --cidr-block "10.0.0.0/16" --no-amazon-provided-ipv6-cidr-block --instance-tenancy default

aws ec2 delete-vpc --vpc-id vpc-08ccb95c7042bc32e

aws ec2 describe-vpcs --filters Name=Name,Values=demo

aws ec2 modify-vpc-attribute --enable-dns-hostnames --enable-dns-support --vpc-id <value>

aws ec2 modify-vpc-attribute --no-enable-dns-hostnames --vpc-id vpc-08f37ad6c277af8a5

# AWS CloudFormation

- AWS CLI: https://docs.aws.amazon.com/cli/latest/reference/cloudformation/index.html


- aws cloudformation create-stack --stack-name myvpc --template-body file://csye6225-infra.yaml


- aws cloudformation create-stack --stack-name myvpcwithparam         --template-body file://csye6225-infra.yaml --parameters ParameterKey=VpcCidrBlock,ParameterValue="10.1.1.0/24"

# Lambda Stack
- aws cloudformation create-stack  --profile admin-prod --region us-east-1 --stack-name mylambdastack --template-body   file://csye6225-infra-lambda.yaml \
--parameters ParameterKey=S3CodeDeployBucket,ParameterValue=rk.csye6225.com\
 ParameterKey=domain,ParameterValue=prod.raghavkhanna.me\
 ParameterKey=fromemail,ParameterValue=info@prod.raghavkhanna.me\
 --capabilities CAPABILITY_NAMED_IAM

 # Application Stack

 - aws cloudformation --profile admin-prod --region us-east-1 create-stack \  --stack-name myNetworkStack \
   --template-body file://network.yaml

- aws cloudformation --profile admin-prod --region us-east-1 create-stack --stack-name application-stack \                             
--template-body file://auto-scaling.yaml \
--parameters \
ParameterKey=NetworkStackName,ParameterValue=myNetworkStack \
ParameterKey=AmiID,ParameterValue=ami-01390c1685b082965 \
--capabilities CAPABILITY_NAMED_IAM --on-failure DELETE

 - aws cloudformation --profile admin-prod --region us-east-1 create-stack \
  --stack-name myStack1 \
  --template-body file://csye6225-infra.yaml \
  --parameters ParameterKey=AmiID,ParameterValue=ami-054361875ff03b848 \
  --capabilities CAPABILITY_NAMED_IAM

 - scp -i /Users/raghavkhanna/.ssh/aws /Users/raghavkhanna/Desktop/webapp/webapp/target/webapp-0.0.1-SNAPSHOT.jar ubuntu@ec2-3-238-91-139.compute-1.amazonaws.com:~

 # Scale up the instance

 seq 1 100000 | xargs -n1 -P20  curl "https://prod.raghavkhanna.me/healthz"

 # Certificate Upload

 aws acm import-certificate --certificate fileb://Certificate.pem --certificate-chain fileb://CertificateChain.pem --private-key fileb://PrivateKey.pem

