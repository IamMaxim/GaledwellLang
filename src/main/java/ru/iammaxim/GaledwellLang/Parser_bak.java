package ru.iammaxim.GaledwellLang;

/**
 * Created by maxim on 2/12/17 at 12:14 AM.
 */
public class Parser_bak {
/*    public ArrayList<ValueFunction> parse(String src) {
        return parseFunctions(src);
    }

    public ArrayList<ValueFunction> parseFunctions(String src) {
        ArrayList<Character> newSrc = new ArrayList<>(src.length());
        for (int i = 0; i < src.length(); i++) {
            newSrc.add(src.charAt(i));
        }

        ArrayList<Integer> positions = new ArrayList<>();
        //add start of src
        positions.add(0);

        boolean isComment = false;
        int level = 0;
        for (int i = 0; i < newSrc.size(); i++) {
            char c = newSrc.get(i);

            //end of comment
            if (isComment && c == '\n') {
                isComment = false;

                //remove char and go to current index again
                newSrc.remove(i);
                i--;

                continue;
            }

            //start of comment
            if (i < newSrc.size() - 2 && c == '/' && newSrc.get(i+1) == '/') {
                isComment = true;
            }

            //is in comment
            if (isComment) {
                //remove char and go to current index again
                newSrc.remove(i);
                i--;

                continue;
            }

            if (c == '{') {
                if (level == 0)
                    positions.add(i);
                level++;
            }
            if (c == '}') {
                level--;
                if (level == 0)
                    positions.add(i+1);
            }
        }
        //add end of src
        positions.add(newSrc.size());

        //update src, excluding comments

        StringBuilder sb = new StringBuilder(newSrc.size());
        for (char c : newSrc)
            sb.append(c);
        src = sb.toString();

        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 0; i < positions.size()-1; i++) {
            String s = src.substring(positions.get(i), positions.get(i+1)).trim();
            if (s.isEmpty()) continue;
            tokens.add(s);
        }

        ArrayList<ValueFunction> functions = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i += 2) {
            String name_and_args = tokens.get(i);
            int divisionIndex = name_and_args.indexOf("(");
            String name = name_and_args.substring(0, divisionIndex).trim();
            String[] args = Utils.splitByComma(name_and_args.substring(divisionIndex + 1, name_and_args.length() - 1));
            functions.add(new FunctionParsed(name, args, tokens.get(i+1)));
        }

        return functions;
    }*/
}
