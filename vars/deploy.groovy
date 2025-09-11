def call() {
    script {
        dir("${env.WORKSPACE}") {
            echo '🚀 Deploying...'
            sh 'docker-compose up -d'
        }
    }
}
