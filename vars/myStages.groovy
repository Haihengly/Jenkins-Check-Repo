def myStages() {
    stage('Checkout') {
        steps {
            checkoutrepo('main', 'https://github.com/Haihengly/Products-Jenkins')
        }
    }
    stage('Build') {
        steps {
            script { build() }
        }
    }
    stage('Deploy') {
        steps {
            script { deploy() }
        }
    }
}
