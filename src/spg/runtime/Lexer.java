package spg.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lexer {
    private List<Pattern> patterns;
    private List<Boolean> ignore;
    private int ntNum, tNum;
    private String s;
    private Terminal next;

    public void setConfig(Configuration config) {
        patterns = new ArrayList<>();
        patterns.addAll(config.getValues().stream().map(Pattern::compile).collect(Collectors.toList()));
        ignore = config.getIgnore();
        ntNum = config.getNonTerminalNumber();
        tNum = config.getTerminalNumber();
    }

    public void init(String s) {
        this.s = s;
    }

    public Terminal getNext() {
        return next;
    }

    public void next() throws ParseException {
        for (int i = 0; i < patterns.size(); i++) {
            Matcher m = patterns.get(i).matcher(s);
            if (m.lookingAt()) {
                String x = m.group();
                s = s.substring(x.length());
                if (ignore.get(i)) {
                    i = -1;
                } else {
                    next = new Terminal(ntNum + i, x);
                    return;
                }
            }
        }
        if (s.length() == 0) {
            next = new Terminal(tNum + ntNum, "$");
            return;
        }
        throw new ParseException("");
    }
}
