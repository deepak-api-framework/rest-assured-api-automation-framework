pipeline {

    agent any

    options {
        skipDefaultCheckout(true)
    }

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {

        stage('Checkout') {

            steps {

                git branch: 'main',
                    url: 'https://github.com/deepak-api-framework/rest-assured-api-automation-framework'
            }
        }

        stage('Build Docker Image') {
    	steps {
        bat 'docker build -t rest-api-framework .'
    		}
		}

		stage('Run Tests Inside Docker') {

    steps {

        bat 'docker run -v "%WORKSPACE%/reports:/app/reports" rest-api-framework'
    }
}
        
    }

    post {

        always {

            archiveArtifacts artifacts: 'reports/index.html',
                fingerprint: true
                
            publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'reports',
            reportFiles: 'index.html',
            reportName: 'Extent Report'
        ])
        }
    }
}