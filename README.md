# kogito-aws-lambda

This is a combination of https://quarkus.io/guides/amazon-lambda-http and https://quarkus.io/guides/kogito focused
on native builds. Kogito is a new framework for drools and jbpm and that will automatically build JAX-RS scaffolding
for your rules and process files. The amazon lambda http extension will expose those JAX-RS endpoints over AWS
lambda and API gateway by fowarding all requests received by the lambda to localhost host via netty.

On my tests with 128 MB, cold starts with first execution was 1100ms. Second execution was 8ms. Sweeeeeeet.

TODO:
* Add kotlin support https://quarkus.io/guides/kotlin

Steps to deploy to AWS Lambda.
1. Install SAM local: https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html
2. Install graal. This was tested against 20.1.0-java8 installed via sdkman https://sdkman.io/
3. Build the native image: `./mvnw clean install -Dnative -Dnative-image.docker-build=true`
4. Login into your AWS account for your cli and make sure you have an s3 bucket you can use for deploy artifacts.
5. Package it for AWS lambda: `sam package --template-file target/sam.native.yaml --output-template-file packaged-native.yaml --s3-bucket <your-bucket-here>`
6. Deploy it for AWS lambda: `sam deploy --template-file packaged-native.yaml --capabilities CAPABILITY_IAM --stack-name <your-stack-name-here>`
7. Get the URL of the API Gateway exposing your lambda (see outputs): `aws cloudformation describe-stacks --stack-name <your-stack-name>`
8. Hit the URL: 
```
 curl -X POST <your-endpoint-here>/persons \                                                                    
    -H 'content-type: application/json' \
    -H 'accept: application/json' \
    -d '{"person": {"name":"John Quark", "age": 20}}'

```