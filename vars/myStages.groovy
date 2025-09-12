def call() {
    
    checkoutrepo('main', 'https://github.com/Haihengly/Products-Jenkins')
           
    build()
                    
    deploy()
                  
}
