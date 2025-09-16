def call(Map config) { 
    echo "Building Docker images with docker-compose..."
    sh "docker-compose build --build-arg VERSION=${config.version}"
}


