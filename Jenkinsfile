pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = 'ananthak22'
        IMAGE_NAME = "${env.DOCKERHUB_USERNAME}/calculator"
        IMAGE_TAG = "latest"
        CONTAINER_NAME = 'calculator_container'
        EMAIL_RECIPIENTS = 'ananthakk26@gmail.com'
    }

    stages {
        // Build and Test the Java Application
        stage('Build & Test') {
            steps {
                script {
                    echo ' Building and testing the application using Maven '
                    sh 'mvn clean package'
                }
            }
        }

        // Build the Docker Image
        stage('Build Docker Image') {
            steps {
                script {
                    echo " Building Docker image: ${IMAGE_NAME}:${IMAGE_TAG} "
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        // Push Docker Image to Docker Hub
        stage('Push to Docker Hub') {
            steps {
                script {
                    echo " Pushing Docker image to Docker Hub "
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        // Log in to Docker Hub using the credentials
                        sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                        // Push the image with its tag
                        sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                    }
                }
            }
        }

        //deploy using ansible
        stage('Deploy with Ansible') {
            steps {
                script {
                    echo "Deploying application using Ansible"
                    sh "ansible-playbook -i inventory.ini deploy.yml --extra-vars 'image_name=${IMAGE_NAME}:${IMAGE_TAG}'"
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Pipeline was successful. Sending notification...'
                emailext (
                    subject: "SUCCESS: Pipeline '${env.JOB_NAME}' - Build #${env.BUILD_NUMBER}",
                    body: """<p>The pipeline <b>${env.JOB_NAME}</b> build #${env.BUILD_NUMBER} completed successfully.</p>
                               <p>Check the build output here: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                    to: "${env.EMAIL_RECIPIENTS}",
                    mimeType: 'text/html'
                )
            }
        }

        failure {
            script {
                echo 'Pipeline failed. Sending notification...'
                emailext (
                    subject: "FAILED: Pipeline '${env.JOB_NAME}' - Build #${env.BUILD_NUMBER}",
                    body: """<p>The pipeline <b>${env.JOB_NAME}</b> build #${env.BUILD_NUMBER} failed.</p>
                               <p>Check the build console output for errors: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                    to: "${env.EMAIL_RECIPIENTS}",
                    mimeType: 'text/html'
                )
            }
        }

        always {
            echo 'Cleaning up...'
            sh "docker rm -f ${CONTAINER_NAME} || true"
            sh "docker rmi -f ${IMAGE_NAME}:${IMAGE_TAG} || true" // Added -f to force removal
            cleanWs()
        }
    }
}