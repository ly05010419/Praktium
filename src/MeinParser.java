/* Generated By:JavaCC: Do not edit this line. MeinParser.java */
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.Queue;
import java.util.LinkedList;

public class MeinParser implements MeinParserConstants {
  // static Stack argStack = new Stack();
  static Queue < String > argStack = new LinkedList < String > ();

  static HashMap < String, String > inputVarsMap = new HashMap < String, String > ();

  static HashMap < String, String > variablesMap = new HashMap < String, String > ();

  static HashMap < String, String > tempVarsMap = new HashMap < String, String > ();

  static HashMap < String, String > tempVarsMap2 = new HashMap < String, String > ();

  static HashMap < String, String > markerMap = new HashMap < String, String > ();

  static ArrayList < String > variableList = new ArrayList < String > ();

  static int zeileNummer = 0;

  static StringBuffer code = new StringBuffer();

  static int varsNum = 1;

  static boolean label = false; //false, true

  public static void main(String args []) throws Exception
  {
    FileReader reader = new FileReader("./praktium/test2.txt");
    MeinParser meinParser = new MeinParser(reader);
    //meinParser.vars();
    //meinParser.assignment();
    //meinParser.whileStmnt();
    meinParser.program();
    meinParser.wrieteFile(code.toString());
  }

  public void wrieteFile(String str) throws Exception
  {
    File writename = new File("./praktium/code.txt");
    writename.createNewFile();
    BufferedWriter out = new BufferedWriter(new FileWriter(writename));
    out.write(str);
    out.flush();
    out.close();
  }

  public void createVars(String image)
  {
    System.out.println(";"+image+" zu "+"R" + varsNum+",");
    inputVarsMap.put(image, "R" + varsNum++);
    variablesMap.put(image, "R" + varsNum++);
    tempVarsMap.put(image, "R" + varsNum++);
    tempVarsMap2.put(image, "R" + varsNum++);
    varsNum++;
  }

  public void createCopyBefehle(StringBuffer s, String var)
  {
    label(s);
    s.append(variablesMap.get(token.image) + " = 0 ;COPY (" + variablesMap.get(var) + "," + inputVarsMap.get(var) + ")  \u005cn");
    label(s);
    markerMap.put("ersteAnfangMake", "" + zeileNummer);
    s.append("if " + inputVarsMap.get(var) + " == 0 goto " + (zeileNummer + 4) + "\u005cn");
    label(s);
    s.append(inputVarsMap.get(token.image) + "--\u005cn");
    label(s);
    s.append(tempVarsMap.get(token.image) + "++\u005cn");
    label(s);
    s.append("goto " + markerMap.get("ersteAnfangMake") + "\u005cn");
    label(s);
    markerMap.put("ersteAnfangMake2", "" + zeileNummer);
    s.append("if " + tempVarsMap.get(var) + " == 0 goto " + (zeileNummer + 5) + "\u005cn");
    label(s);
    s.append(tempVarsMap.get(var) + "--\u005cn");
    label(s);
    s.append(variablesMap.get(var) + "++\u005cn");
    label(s);
    s.append(inputVarsMap.get(var) + "++\u005cn");
    label(s);
    s.append("goto " + markerMap.get("ersteAnfangMake2") + "\u005cn");
  }

  public void label(StringBuffer s)
  {
    zeileNummer++;
    if (label) s.append(zeileNummer + ":");
  }

  public void zuweizungBefehle(StringBuffer s, String var1, String var)
  {
    label(s);
    s.append(tempVarsMap2.get(var) + " = 0 ;COPY (" + tempVarsMap2.get(var) + "," + inputVarsMap.get(var) + ")  \u005cn");
    label(s);
    markerMap.put("ersteAnfangMake", "" + zeileNummer);
    s.append("if " + inputVarsMap.get(var) + " == 0 goto " + (zeileNummer + 4) + "\u005cn");
    label(s);
    s.append(inputVarsMap.get(var) + "--\u005cn");
    label(s);
    s.append(tempVarsMap.get(var) + "++\u005cn");
    label(s);
    s.append("goto " + markerMap.get("ersteAnfangMake") + "\u005cn");
    label(s);
    markerMap.put("ersteAnfangMake2", "" + zeileNummer);
    s.append("if " + tempVarsMap.get(var) + " == 0 goto " + (zeileNummer + 5) + "\u005cn");
    label(s);
    s.append(tempVarsMap.get(var) + "--\u005cn");
    label(s);
    s.append(tempVarsMap2.get(var) + "++\u005cn");
    label(s);
    s.append(inputVarsMap.get(var) + "++\u005cn");
    label(s);
    s.append("goto " + markerMap.get("ersteAnfangMake2") + "\u005cn");
    label(s);
    s.append(tempVarsMap2.get(var) + "++\u005cn");
    label(s);
    s.append(inputVarsMap.get(var1) + "=0\u005cn");
    label(s);
    markerMap.put("anweizungAnfangMake", zeileNummer + "");
    s.append("if " + tempVarsMap2.get(var) + " == 0 goto " + (zeileNummer + 4) + "\u005cn");
    label(s);
    s.append(tempVarsMap2.get(var) + "--" + "\u005cn");
    label(s);
    s.append(inputVarsMap.get(var1) + "++" + "\u005cn");
    label(s);
    s.append("goto " + markerMap.get("anweizungAnfangMake") + "\u005cn");
  }

  final public void input() throws ParseException {
    jj_consume_token(IN);
    jj_consume_token(IDENT);
    this.variableList.add(token.image);
    createVars(token.image);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case KOMMA:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(KOMMA);
      jj_consume_token(IDENT);
      this.variableList.add(token.image);
      createVars(token.image);
    }

  }

  final public void output() throws ParseException {
    jj_consume_token(OUT);
    jj_consume_token(IDENT);
    createVars(token.image);

  }

  final public void vars() throws ParseException {
    jj_consume_token(VAR);
    jj_consume_token(LRUNDKLAMMER);
    jj_consume_token(IDENT);
    createVars(token.image);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case KOMMA:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(KOMMA);
      jj_consume_token(IDENT);
      createVars(token.image);
    }
    jj_consume_token(RRUNDKLAMMER);

  }

  final public void condition(StringBuffer s) throws ParseException {
    jj_consume_token(IDENT);
    createCopyBefehle(s, token.image);
    jj_consume_token(NOTEQUAL);
    jj_consume_token(IDENT);
    createCopyBefehle(s, token.image);

  }

  final public String statement() throws ParseException {
  String s = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHILE:
      s = whileStmnt();
      break;
    case IDENT:
      s = assignment();
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMIKOLEN:
      String s1 = null;
      jj_consume_token(SEMIKOLEN);
      s1 = statement();
      s = s + "" + s1;
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    //    System.out.println("--Ein gueltiges statement!");
    //    System.out.println(s);
    //    System.out.println("++Ein gueltiges statement!");
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String whileStmnt() throws ParseException {
  StringBuffer s = new StringBuffer();
  String statement = null;
    jj_consume_token(WHILE);
    condition(s);
    jj_consume_token(DO);
    jj_consume_token(BEGIN);
    label(s);
    markerMap.put("AnfangWhileStmnt", "" + zeileNummer);
    s.append("if " + variablesMap.get(this.variableList.get(0)) + "==0 goto " + (zeileNummer + 5) + "\u005cn");
    label(s);
    s.append("if " + variablesMap.get(this.variableList.get(1)) + "==0 goto " + (zeileNummer + 5) + "\u005cn");
    label(s);
    s.append(variablesMap.get(this.variableList.get(0)) + "--\u005cn");
    label(s);
    s.append(variablesMap.get(this.variableList.get(1)) + "--\u005cn");
    label(s);
    s.append("goto " + markerMap.get("AnfangWhileStmnt") + "\u005cn");
    label(s);
    s.append("if " + variablesMap.get(this.variableList.get(1)) + "==0 goto StatementEndLabel \u005cn");
    statement = statement();
    s.append(statement);
    jj_consume_token(END);
    label(s);
    s.append("goto 1\u005cn");
    s = new StringBuffer(s.toString().replace("StatementEndLabel", (zeileNummer + 1) + ""));
    //    System.out.println("----whileStmnt!----");
    //    System.out.println(s);
    //    System.out.println("++++whileStmnt!++++");
    {if (true) return s.toString();}
    throw new Error("Missing return statement in function");
  }

  final public String assignment() throws ParseException {
  StringBuffer s = new StringBuffer();
    jj_consume_token(IDENT);
    String var1 = token.image;
    label(s);
    s.append("" + inputVarsMap.get(var1));
    jj_consume_token(ASSIGN);
    s.append("=");
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ZERO:
      jj_consume_token(ZERO);
      s.append("0\u005cn");
      break;
    case IDENT:
      jj_consume_token(IDENT);
      String var2 = token.image;
      s = new StringBuffer();
      if (var1.equals(var2))
      {
        zeileNummer--;
        label(s);
        s.append("" + inputVarsMap.get(var1) + "++\u005cn");
      }
      else
      {
        zeileNummer--;
        zuweizungBefehle(s, var1, var2);
      }
      jj_consume_token(PLUS);
      jj_consume_token(EINS);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    //    System.out.println("----------assignment----------");
    //    System.out.println(s);
    //    System.out.println("++++++++++assignment++++++++++");
    {if (true) return s.toString();}
    throw new Error("Missing return statement in function");
  }

  final public void program() throws ParseException {
  String s = "";
    jj_consume_token(IDENT);
    jj_consume_token(LRUNDKLAMMER);
    input();
    jj_consume_token(SEMIKOLEN);
    output();
    jj_consume_token(RRUNDKLAMMER);
    vars();
    jj_consume_token(SEMIKOLEN);
    s = statement();
    code.append(s);
    jj_consume_token(0);
  }

  /** Generated Token Manager. */
  public MeinParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[5];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x40000,0x40000,0x4020,0x200000,0x5000,};
   }

  /** Constructor with InputStream. */
  public MeinParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MeinParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MeinParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MeinParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MeinParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MeinParser(MeinParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MeinParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[22];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 5; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 22; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
