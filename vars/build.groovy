def call() {
    script {
        dir("${env.WORKSPACE}") {
            echo '📦 Building...'
            sh 'docker-compose build'
        }
    }
}
