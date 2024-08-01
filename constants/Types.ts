export interface CloudflareEmailRouteRule {
    actions: {
        type: 'forward' | 'drop' | 'worker';
        value: string[];
    }
    enabled: boolean;
    id: string;
    matchers: { 
        field: 'to';
        type: 'literal';
        value: string;
    }[];
    name: string;
    priority: number;
    
}