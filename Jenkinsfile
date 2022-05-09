pipeline {
    agent any

    environment{
        imageName = ""
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
                script{
                     imageName = docker.build "biswa2015/cqcmp:latest"
                 }
            }
        }
        stage('Push image to docker hub'){
            steps {
                script{
                    docker.withRegistry('', 'dockerhub-cred-biswa') {
                       imageName.push()
                    }
                }
            }
        }
        stage('Pull image & deploy'){
            steps {
                ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'inventory', playbook: 'playbook.yml'
            }
        }
    }
}
