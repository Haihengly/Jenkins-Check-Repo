// def call(String branch, String repoUrl, Map buildParams = [:]) {
//     return [
//         [name: 'Checkout', action: { checkoutrepo(branch, repoUrl) }],
//         [name: 'Build', action: { build() }],
//         [name: 'Deploy', action: { deploy() }]
//     ]
// }


def call(Map config) {
    return [
        [
            name: 'Checkout', 
            action: { -> checkoutrepo(config.branch, config.repoUrl) }
        ],
        [
            name: 'Build', 
            action: { -> build(config) } // can use config.envName, config.version, etc.
        ],
        [
            name: 'Deploy', 
            action: { -> 
                if(config.deploy) {
                    deploy(config)
                } else {
                    echo "Skipping deploy"
                }
            }
        ] 
        // add more stages if needed
    ]
}
