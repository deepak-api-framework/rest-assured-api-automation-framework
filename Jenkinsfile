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

        stage('Build') {

            steps {

                bat 'mvn clean compile'
            }
        }

        stage('Run Regression Suite') {

            steps {

                bat 'mvn test -DsuiteXmlFile=regression.xml'
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