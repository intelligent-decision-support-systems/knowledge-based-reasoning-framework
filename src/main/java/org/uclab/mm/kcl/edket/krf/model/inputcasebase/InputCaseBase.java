package org.uclab.mm.kcl.edket.krf.model.inputcasebase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputCaseBase implements Serializable {

    private static final long serialVersionUID = 9043723169288741433L;
    private List<Map<String, List<String>>> inputCaseBase;

    public InputCaseBase() {
        inputCaseBase = new ArrayList<Map<String, List<String>>>();
    }

    public List<Map<String, List<String>>> getInputCaseBase() {
        return inputCaseBase;
    }

    public void setInputCaseBase(List<Map<String, List<String>>> inputCaseBase) {
        this.inputCaseBase = inputCaseBase;
    }

    public InputCaseBase addInputFact(String key, String... values) {
        if (inputCaseBase.get(inputCaseBase.size() - 1).containsKey(key)) {
            inputCaseBase.get(inputCaseBase.size() - 1).get(key)
                    .addAll(Arrays.asList(values));
        } else {
            inputCaseBase.get(inputCaseBase.size() - 1).put(key,
                    Arrays.asList(values));
        }
        return this;
    }

    public InputCaseBase createInputCase() {
        inputCaseBase.add(new HashMap<String, List<String>>());
        return this;
    }

    @Override
    public String toString() {
        return "InputCaseBase [inputCaseBase=" + inputCaseBase + "]";
    }
}
