import Ionicons from "@expo/vector-icons/Ionicons";
import {
  StyleSheet,
  Image,
  Platform,
  Text,
  TextInput,
  Button,
  Linking,
} from "react-native";

import { Collapsible } from "@/components/Collapsible";
import { ExternalLink } from "@/components/ExternalLink";
import ParallaxScrollView from "@/components/ParallaxScrollView";
import { ThemedText } from "@/components/ThemedText";
import { ThemedView } from "@/components/ThemedView";

export default function LogInScreen() {
  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: "#D0D0D0", dark: "#353636" }}
      headerImage={
        <Ionicons size={310} name="code-slash" style={styles.headerImage} />
      }
    >
      <ThemedView style={styles.titleContainer}>
        <ThemedText type="title">Log in with cloudflare</ThemedText>
      </ThemedView>
      <ThemedText>
        Use your cloudflare API key to manage your emails from the app.
        </ThemedText>

              <Button
                title="Get API key"
                color={Platform.OS === "ios" ? "#007AFF" : "#3F51B5"}
                onPress={() => {
                  Linking.openURL("https://dash.cloudflare.com/profile/api-tokens");
                }}
              ></Button>

        <ThemedText type="subtitle">Email</ThemedText>
        <TextInput
            
            placeholder="test@example.com"></TextInput>

      <ThemedText type="subtitle">API key</ThemedText>
      <TextInput
        style={{ fontFamily: "monospace" }}
        placeholder="r:e8h:79f93247fh7:8ckm32ro;;:xxaqsa2"
      ></TextInput>

      <ThemedText type="subtitle">Account ID</ThemedText>
      <TextInput
        style={{ fontFamily: "monospace" }}
        placeholder="1234567890"
      ></TextInput>

    </ParallaxScrollView>
  );
}

const styles = StyleSheet.create({
  headerImage: {
    color: "#808080",
    bottom: -90,
    left: -35,
    position: "absolute",
  },
  titleContainer: {
    flexDirection: "row",
    gap: 8,
  },
});
