
# Cloud Computing Initiative: Web Application Suite

## Overview
Welcome to our comprehensive Cloud Computing Initiative, featuring a robust web application suite designed with modern cloud-native principles.

## Key Features

### **Web Application Functionality**

- **Robust Account Management**
  - Secure user profile creation and email-based verification process.
  - Ensures that only authenticated users can engage with critical endpoints.

- **Sophisticated Document Management**
  - Full document lifecycle management capabilities within the app interface.
  - Backed by AWS S3 for reliable and scalable storage solutions.

- **Integrated Email Notification System**
  - Utilizes AWS SNS for triggering AWS Lambda functions.
  - Streamlines the process of sending out verification emails via AWS SES.
  - Keeps a detailed log of email transactions and tokens within DynamoDB.

### **Infrastructure as Code**

- **AWS CloudFormation**
  - Architect and deploy essential AWS resources with precision.
  - Configures networking components, auto-scaling groups, and database services via RDS.

- **CI/CD Pipeline**
  - GitHub Actions workflow to continuously integrate and deploy.
  - Automates Amazon Machine Image (AMI) creation upon each code commit.

### **Serverless Architecture**

- **Lambda Functions**
  - Facilitates automated email notifications.
  - Ensures detailed record-keeping within DynamoDB for transparency and auditing.

## Technical Stack Breakdown

- **Backend:** Java Spring Boot, Utilizing AWS EC2, S3, RDS, and Lambda for a robust server environment.
- **CI/CD:** Leveraging GitHub Actions and Packer for streamlined build and release processes.
- **Database:** AWS RDS, ensuring a high-performance data management system.
- **Email Services:** AWS SES and SNS, for reliable and efficient communication.
- **Infrastructure Provisioning:** Crafted with AWS CloudFormation for a solid foundation.

---

Embrace the future of cloud computing with our cutting-edge web application suite.
