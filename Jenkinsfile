pipeline {
    agent any

    environment{
        DOCKERHUB_CREDENTIALS=credentials('docker-cred-biswa')
    }

    stages {
        stage('Clone project') {
            steps {
                git 'https://github.com/biswa2015/CovidQuarantineCentreManagementPortal.git'
                echo 'Project is cloned successfully'
            }
        }
        stage('Build'){
            steps {
                sh 'mvn clean install'
                echo 'Maven build completed'
            }
        }
        stage('Test'){
            steps {
                sh 'mvn test'
                echo 'Testing completed'
            }
        }
        stage('Build image'){
             steps {
                echo 'creating docker  image'
                sh 'docker build -t cqcmp:latest .'
                echo 'docker image created'
            }
        }
        stage('Push image to docker hub'){
            steps {
                echo 'docker tag'
                sh 'docker tag cqcmp biswa2015/cqcmp:latest'
                echo 'docker login'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                echo 'Pushing image to hub'
                sh 'docker push biswa2015/cqcmp'
                echo 'docker logout'
                sh 'docker logout'
            }
        }
        stage('Pull image & deploy'){
            steps {
                ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'inventory', playbook: 'playbook.yml'
            }
        }
    }
}
