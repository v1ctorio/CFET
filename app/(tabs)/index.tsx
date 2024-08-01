import { Image, StyleSheet, Platform } from 'react-native';

import { HelloWave } from '@/components/HelloWave';
import ParallaxScrollView from '@/components/ParallaxScrollView';
import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import { CloudflareEmailRouteRule} from '../../constants/Types'

export default function HomeScreen() {

  const ForwardRuleCard = (rule: CloudflareEmailRouteRule) => {

    return (
      <ThemedView>
        <ThemedText>{rule.name}</ThemedText>
      </ThemedView>
    );
    }

  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: '#A1CEDC', dark: '#1D3D47' }}
      headerImage={
        <Image
          source={require('@/assets/images/partial-react-logo.png')}
          style={styles.reactLogo}
        />
      }>
      <ThemedView style={styles.titleContainer}>
        <ThemedText type="title">Email manager</ThemedText>
        
      </ThemedView>
      <ThemedView style={styles.stepContainer}>
        {list_routing_rules_example.result.map((rule) => {
          
          if (rule.actions[0].type === 'forward') {
           return ForwardRuleCard(rule)
          }

})}
      </ThemedView>
    </ParallaxScrollView>
  );
}

const styles = StyleSheet.create({
  titleContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  stepContainer: {
    gap: 8,
    marginBottom: 8,
  },
  reactLogo: {
    height: 178,
    width: 290,
    bottom: 0,
    left: 0,
    position: 'absolute',
  },
});


const list_routing_rules_example = {
  "errors": [],
  "messages": [],
  "success": true,
  "result": [
    {
      "actions": [
        {
          "type": "forward",
          "value": [
            "destinationaddress@example.net"
          ]
        }
      ],
      "enabled": true,
      "id": "a7e6fb77503c41d8a7f3113c6918f10c",
      "matchers": [
        {
          "field": "to",
          "type": "literal",
          "value": "test@example.com"
        }
      ],
      "name": "Send to user@example.net rule.",
      "priority": 0,
      "tag": "a7e6fb77503c41d8a7f3113c6918f10c"
    }
  ],
  "result_info": {
    "count": 1,
    "page": 1,
    "per_page": 20,
    "total_count": 1
  }
}