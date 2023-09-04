package nl.bigo.pcreparser;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InternalNodeListener extends PCREParserBaseListener {

    private static final Set<String> ignored = new HashSet<String>(){{
        add(PCREParser.DigitsContext.class.getSimpleName());
        add(PCREParser.DigitContext.class.getSimpleName());
        add(PCREParser.HexContext.class.getSimpleName());
        add(PCREParser.UtfContext.class.getSimpleName());
        add(PCREParser.UcpContext.class.getSimpleName());
        add(PCREParser.No_auto_possessContext.class.getSimpleName());
        add(PCREParser.No_start_optContext.class.getSimpleName());
        add(PCREParser.CrContext.class.getSimpleName());
        add(PCREParser.LfContext.class.getSimpleName());
        add(PCREParser.CrlfContext.class.getSimpleName());
        add(PCREParser.AnycrlfContext.class.getSimpleName());
        add(PCREParser.AnyContext.class.getSimpleName());
        add(PCREParser.Limit_matchContext.class.getSimpleName());
        add(PCREParser.Limit_recursionContext.class.getSimpleName());
        add(PCREParser.LettersContext.class.getSimpleName());
        add(PCREParser.LetterContext.class.getSimpleName());
        add(PCREParser.Character_class_atomContext.class.getSimpleName());
        add(PCREParser.Character_class_rangeContext.class.getSimpleName());
        add(PCREParser.Posix_character_classContext.class.getSimpleName());
        add(PCREParser.ExprContext.class.getSimpleName());
        add(PCREParser.ElementContext.class.getSimpleName());
        add(PCREParser.AtomContext.class.getSimpleName());
        add(PCREParser.AlternationContext.class.getSimpleName());
        add(PCREParser.Option_setting_flagContext.class.getSimpleName());
        add(PCREParser.Bsr_anycrlfContext.class.getSimpleName());
        add(PCREParser.Bsr_unicodeContext.class.getSimpleName());
        add(PCREParser.AcceptContext.class.getSimpleName());
        add(PCREParser.FailContext.class.getSimpleName());
        add(PCREParser.MarkContext.class.getSimpleName());
        add(PCREParser.CommitContext.class.getSimpleName());
        add(PCREParser.PruneContext.class.getSimpleName());
        add(PCREParser.SkipContext.class.getSimpleName());
        add(PCREParser.ThenContext.class.getSimpleName());
        add(PCREParser.Character_class_range_atomContext.class.getSimpleName());
        add(PCREParser.NameContext.class.getSimpleName());
    }};

    private final List<String> rules = new ArrayList<>();

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

        if (ignored.contains(ctx.getClass().getSimpleName())) {
            return;
        }

        String rule = ctx.getClass()
                .getSimpleName()
                .replaceAll("Context$", "");

        this.rules.add(Character.toLowerCase(rule.charAt(0)) + rule.substring(1));
    }

    public String lastRule() {
        return this.rules.get(this.rules.size() - 1);
    }
}
