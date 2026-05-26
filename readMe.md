AWS Glue 5.0 Local Development: The Complete Guide
This document contains everything needed to set up, build, and run an AWS Glue 5.0 Scala job on your local machine using Docker.

1. Prerequisites
   Ensure the following are installed and running:

Java: JDK 8 or 17

Scala: 2.12.18

SBT: (Scala Build Tool)

Docker Desktop: Must be running

AWS CLI: Configured via aws configure (required for S3/Glue Catalog access)

2. One-Time Setup
   Pull the official AWS Glue 5.0 image to your local machine:


docker pull public.ecr.aws/glue/aws-glue-libs:5


3. Project Configuration (build.sbt)
   Create a build.sbt file in your project root. Check out the build.sbt in this repo- While using intellij Idea
the project structure and folder is automatically created along with build.sbt.


5. Build and Execution Workflow
   Step A: Package the JAR

sbt commands to run in sbt shell:

clean

compile

package   (note this creates a thin JAR which is exactly what we want)


Step B: Run in Docker 
Run this command from your project root. It mounts your local AWS keys and your project code into the container.
Remember to substitute the class with proper name of the class to be called.

docker run -it --rm -v "${HOME}\.aws:/home/hadoop/.aws" -v "${PWD}:/home/hadoop/workspace/" public.ecr.aws/glue/aws-glue-libs:5 spark-submit --class "org.apple.code1" /home/hadoop/workspace/target/scala-2.12/scala_awsglue5_project_2.12-0.1.0-SNAPSHOT.jar


6. Important Reference Notes
   Pathing Logic
   -v "${HOME}\.aws:/home/hadoop/.aws": Connects your laptop's AWS credentials to the container so you can read/write S3.

-v "${PWD}:/home/hadoop/workspace/": Maps your current project folder to the container's internal workspace.

--class "org.apple.code1": Tells Spark exactly which Scala object to run (it is case-sensitive).

Troubleshooting
Memory: If the container hangs, increase Docker RAM to at least 4GB in Docker Desktop > Settings > Resources.

File Not Found: Ensure you run the Docker command from the root folder where build.sbt is located.

Class Not Found: Double-check that your package and object names in the Scala file match the --class argument in the command.

Cold Start: Spark takes ~1-2 minutes to initialize in the Glue container. If you see INFO logs, it is working.