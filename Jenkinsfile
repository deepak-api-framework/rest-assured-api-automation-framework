pipeline {

    agent any

    tools {
        maven 'Maven'
        jdk 'Java21'
    }

    stages {

        stage('Checkout') {

            steps {
                git 'https://github.com/deepak-api-framework/rest-assured-api-automation-framework'
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

            archiveArtifacts artifacts: 'test-output/ExtentReports.html',
                    fingerprint: true
        }
    }
}