pipeline {

    agent any
    stages {
         stage ('Checkout GIT'){
                steps {
                    echo 'pulling... ';
                        git branch : 'souhail',
                        url : 'https://github.com/souhail642/devski.git';
                }
            }
               stage('Maven Version') {
            steps {

                // This step cleans and compiles the Maven project
                sh 'mvn -version'
            }
        }
        stage('JDK Version') {
            steps {

                // This step cleans and compiles the Maven project
                sh 'java -version'

        }}
     stage('MVN Clean Compile') {
            steps {

                // This step cleans and compiles the Maven project
                sh 'mvn clean compile'

        }}
           stage('JaCoCo Coverage') {
            steps {
                script {

                    // No need to use 'dir' if the pom.xml is in the root directory
                    sh 'mvn jacoco:report -DskipTests'

            }
        }}


 stage('SonarQube Testing') {
            steps {
                echo "Running Static Code Analysis with SonarQube"
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=asmaasma'
            }
        }
    stage('Build Artifact') {
            steps {
                echo "Building the Artifact (.jar) using Maven"
                sh 'chmod +x ./mvnw'
                sh 'mvn clean package -DskipTests'
                sh"docker stop 5324f109492e"
                sh" docker start c73da796b621"
            }
        }
            stage('Deploy To Nexus') {
            steps {
                    sh 'mvn deploy'
            }
        }


       
        



         stage('docker create image'){
            steps{
                sh 'docker build -t ski .'
            }

          }


           stage('docker push'){
            steps{
                script{
                            sh 'docker login -u chernisouhail -p asmaasmaas'

                        sh 'docker tag ski chernisouhail/gestion-station-ski'
                        sh 'docker push chernisouhail/gestion-station-ski'
                }

            }
           }
                 stage('docker compose'){
            steps {
             sh "docker-compose  up -d"

            }
        }
        }
        }