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
        stage('Build') {
            steps {
                echo "Building....."
                sh '''
                echo "Testing...."
                ls -a
                '''
            }
        }
    }
}
