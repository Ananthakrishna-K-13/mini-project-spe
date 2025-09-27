pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = 'ananthak22'
        IMAGE_NAME = "${env.DOCKERHUB_USERNAME}/calculator"
        IMAGE_TAG = "latest" 
        CONTAINER_NAME = 'calculator_container'
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
                    echo "--- Deploying application using Ansible ---"
                    sh "ansible-playbook -i inventory.ini deploy.yml --extra-vars 'image_name=${IMAGE_NAME}:${IMAGE_TAG}'"
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            sh "docker rm -f ${CONTAINER_NAME} || true"
            sh "docker rmi ${IMAGE_NAME}:${IMAGE_TAG}"
            cleanWs()
        }
    }
}

