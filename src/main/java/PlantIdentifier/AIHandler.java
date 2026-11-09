package PlantIdentifier;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.request.ThinkMode;
import io.github.ollama4j.models.response.OllamaResult;

import java.io.File;
import java.util.List;

public class AIHandler {
    public static final String MODEL_NAME = "qwen3-vl:2b";
    public static final String PROMPT = "You are given an image of a plant." + "You will answer the what the name of the plant is, and if its edible" + "Do not try to explain anything, only answer with the name of the plant and if someone can eat it." + "You will first answer 'Plant Name: ' and then the name of the plant" + "you then will answer 'Edible: ' and then if someone can eat the plant or not";

    public static String analyzeImage(String imagePath) throws Exception {
        Ollama ollama = new Ollama("http://localhost:11434");
        ollama.setRequestTimeoutSeconds(300);

        OllamaResult result = ollama.generate(OllamaGenerateRequest.builder()
                .withModel(MODEL_NAME)
                .withThink(ThinkMode.DISABLED)
                .withPrompt(PROMPT)
                .withImages(List.of(new File(imagePath)))
                .build(), null);
        return result.getResponse().trim();
    }

}
