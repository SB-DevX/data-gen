pipeline {
    agent { 
        node {
            label 'docker-agent-alpine'
            }
      }
    triggers {
        pollSCM '*/5 * * * *'
    }
    stages {
        stage('Init') {
            steps {
                echo "Init...."
                sh '''
                chmod +x gradlew
                ./gradlew javaTool
                '''
            }
        }
        stage('Build') {
            steps {
                echo "Building....."
                sh '''
                ./gradlew build
                '''
            }
        }
    }
}
