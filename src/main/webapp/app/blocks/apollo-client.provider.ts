import { ApolloClient, createNetworkInterface  } from 'apollo-client';

export function provideApolloClient(): ApolloClient {
    const networkInterface = createNetworkInterface(
        {
            uri: '/api/graphql'
        }
    );

    networkInterface.use([{
    /**   
     * Add auth token to GraphQL requests.
     */
    applyMiddleware(req, next) {
        let token = localStorage.getItem('jhi-authenticationtoken') || sessionStorage.getItem('jhi-authenticationtoken');
        
        if (!!token) {
            // Replace double quotes
            token = token.replace(/['"]+/g, '');
            
            req.options.headers = {
                "Authorization": 'Bearer ' + token
            };
        }    

        next();
    }
    }]);

    const client = new ApolloClient({
        networkInterface: networkInterface
    });
    
    return client;
}