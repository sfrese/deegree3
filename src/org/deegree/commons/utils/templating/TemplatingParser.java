
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Tue Jan 12 10:40:20 CET 2010
//----------------------------------------------------

package org.deegree.commons.utils.templating;

import java.util.HashMap;
import java.util.LinkedList;
import java_cup.runtime.*;
import org.deegree.commons.utils.*;
import org.deegree.commons.utils.templating.lang.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Tue Jan 12 10:40:20 CET 2010
  */
public class TemplatingParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public TemplatingParser() {super();}

  /** Constructor which sets the default scanner. */
  public TemplatingParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public TemplatingParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\024\000\002\002\004\000\002\002\004\000\002\002" +
    "\005\000\002\002\004\000\002\002\005\000\002\003\004" +
    "\000\002\003\003\000\002\005\003\000\002\005\003\000" +
    "\002\005\003\000\002\005\003\000\002\005\003\000\002" +
    "\005\003\000\002\005\003\000\002\005\003\000\002\005" +
    "\003\000\002\005\003\000\002\005\003\000\002\004\005" +
    "\000\002\004\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\031\000\006\005\006\006\005\001\002\000\004\002" +
    "\033\001\002\000\004\015\026\001\002\000\030\004\010" +
    "\007\015\010\016\011\020\012\011\013\021\014\013\017" +
    "\022\020\023\021\012\022\014\001\002\000\036\002\ufffb" +
    "\004\010\005\ufffb\006\ufffb\007\015\010\016\011\020\012" +
    "\011\013\021\014\013\017\022\020\023\021\012\022\014" +
    "\001\002\000\036\002\ufffa\004\ufffa\005\ufffa\006\ufffa\007" +
    "\ufffa\010\ufffa\011\ufffa\012\ufffa\013\ufffa\014\ufffa\017\ufffa" +
    "\020\ufffa\021\ufffa\022\ufffa\001\002\000\036\002\ufff2\004" +
    "\ufff2\005\ufff2\006\ufff2\007\ufff2\010\ufff2\011\ufff2\012\ufff2" +
    "\013\ufff2\014\ufff2\017\ufff2\020\ufff2\021\ufff2\022\ufff2\001" +
    "\002\000\036\002\ufff8\004\ufff8\005\ufff8\006\ufff8\007\ufff8" +
    "\010\ufff8\011\ufff8\012\ufff8\013\ufff8\014\ufff8\017\ufff8\020" +
    "\ufff8\021\ufff8\022\ufff8\001\002\000\036\002\ufff1\004\ufff1" +
    "\005\ufff1\006\ufff1\007\ufff1\010\ufff1\011\ufff1\012\ufff1\013" +
    "\ufff1\014\ufff1\017\ufff1\020\ufff1\021\ufff1\022\ufff1\001\002" +
    "\000\036\002\ufff7\004\ufff7\005\ufff7\006\ufff7\007\ufff7\010" +
    "\ufff7\011\ufff7\012\ufff7\013\ufff7\014\ufff7\017\ufff7\020\ufff7" +
    "\021\ufff7\022\ufff7\001\002\000\036\002\ufff6\004\ufff6\005" +
    "\ufff6\006\ufff6\007\ufff6\010\ufff6\011\ufff6\012\ufff6\013\ufff6" +
    "\014\ufff6\017\ufff6\020\ufff6\021\ufff6\022\ufff6\001\002\000" +
    "\036\002\ufff5\004\ufff5\005\ufff5\006\ufff5\007\ufff5\010\ufff5" +
    "\011\ufff5\012\ufff5\013\ufff5\014\ufff5\017\ufff5\020\ufff5\021" +
    "\ufff5\022\ufff5\001\002\000\010\002\001\005\006\006\005" +
    "\001\002\000\036\002\ufff4\004\ufff4\005\ufff4\006\ufff4\007" +
    "\ufff4\010\ufff4\011\ufff4\012\ufff4\013\ufff4\014\ufff4\017\ufff4" +
    "\020\ufff4\021\ufff4\022\ufff4\001\002\000\036\002\ufff3\004" +
    "\ufff3\005\ufff3\006\ufff3\007\ufff3\010\ufff3\011\ufff3\012\ufff3" +
    "\013\ufff3\014\ufff3\017\ufff3\020\ufff3\021\ufff3\022\ufff3\001" +
    "\002\000\036\002\ufff0\004\ufff0\005\ufff0\006\ufff0\007\ufff0" +
    "\010\ufff0\011\ufff0\012\ufff0\013\ufff0\014\ufff0\017\ufff0\020" +
    "\ufff0\021\ufff0\022\ufff0\001\002\000\036\002\ufff9\004\ufff9" +
    "\005\ufff9\006\ufff9\007\ufff9\010\ufff9\011\ufff9\012\ufff9\013" +
    "\ufff9\014\ufff9\017\ufff9\020\ufff9\021\ufff9\022\ufff9\001\002" +
    "\000\004\002\uffff\001\002\000\010\002\ufffc\005\ufffc\006" +
    "\ufffc\001\002\000\004\016\031\001\002\000\010\002\ufffe" +
    "\005\006\006\005\001\002\000\004\002\ufffd\001\002\000" +
    "\012\002\uffee\005\uffee\006\uffee\015\026\001\002\000\010" +
    "\002\uffef\005\uffef\006\uffef\001\002\000\004\002\000\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\031\000\004\002\003\001\001\000\002\001\001\000" +
    "\004\004\026\001\001\000\006\003\016\005\006\001\001" +
    "\000\006\003\024\005\006\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\002" +
    "\023\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\002\027\001\001\000\002" +
    "\001\001\000\004\004\031\001\001\000\002\001\001\000" +
    "\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$TemplatingParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$TemplatingParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$TemplatingParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$TemplatingParser$actions {
  private final TemplatingParser parser;

  /** Constructor */
  CUP$TemplatingParser$actions(TemplatingParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$TemplatingParser$do_action(
    int                        CUP$TemplatingParser$act_num,
    java_cup.runtime.lr_parser CUP$TemplatingParser$parser,
    java.util.Stack            CUP$TemplatingParser$stack,
    int                        CUP$TemplatingParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$TemplatingParser$result;

      /* select the action based on the action number */
      switch (CUP$TemplatingParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // map ::= MAP_KEY_TOKEN MAP_VALUE_TOKEN 
            {
              MapDefinition RESULT =null;
		int keyleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int keyright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		String key = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int valleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int valright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String val = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new MapDefinition();
                                                         RESULT.map.put(key, val); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("map",2, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // map ::= MAP_KEY_TOKEN MAP_VALUE_TOKEN map 
            {
              MapDefinition RESULT =null;
		int keyleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).left;
		int keyright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).right;
		String key = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).value;
		int valleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int valright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		String val = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int defleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int defright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		MapDefinition def = (MapDefinition)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 def.map.put(key, val);
                                                         RESULT = def; 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("map",2, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // templatebody ::= GMLID_TOKEN 
            {
              Object RESULT =null;
		 RESULT = new GMLId(); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // templatebody ::= LINK_CALL_TOKEN 
            {
              Object RESULT =null;
		 RESULT = new Link(); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // templatebody ::= VALUE_TOKEN 
            {
              Object RESULT =null;
		 RESULT = new Value(); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // templatebody ::= NAME_TOKEN 
            {
              Object RESULT =null;
		 RESULT = new Name(); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // templatebody ::= INDEX_CALL_TOKEN 
            {
              Object RESULT =null;
		 RESULT = new Index(); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // templatebody ::= ODD_CALL_TOKEN 
            {
              Object RESULT =null;
		int callleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int callright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String call = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new OddEven(call, true); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // templatebody ::= EVEN_CALL_TOKEN 
            {
              Object RESULT =null;
		int callleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int callright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String call = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new OddEven(call, false); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // templatebody ::= MAP_CALL_TOKEN 
            {
              Object RESULT =null;
		int callleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int callright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String[] call = (String[])((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 if(call[0].equalsIgnoreCase("name")) RESULT = new MapCall(call[1], MapCall.Type.Name);
                                                   else RESULT = new MapCall(call[1], MapCall.Type.Value); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // templatebody ::= FEATURE_TEMPLATE_CALL_TOKEN 
            {
              Object RESULT =null;
		int callleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int callright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String[] call = (String[])((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new FeatureTemplateCall(call[1], call[0]); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // templatebody ::= PROPERTY_TEMPLATE_CALL_TOKEN 
            {
              Object RESULT =null;
		int callleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int callright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String[] call = (String[])((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new PropertyTemplateCall(call[1], call[0]); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // templatebody ::= TEXT_TOKEN 
            {
              Object RESULT =null;
		int textleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int textright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		String text = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = text; 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("templatebody",3, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // template ::= templatebody 
            {
              TemplateDefinition RESULT =null;
		int objleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int objright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		Object obj = (Object)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new TemplateDefinition();
                                                   RESULT.body.add(obj); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("template",1, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // template ::= templatebody template 
            {
              TemplateDefinition RESULT =null;
		int objleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int objright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		Object obj = (Object)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int tmplleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int tmplright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		TemplateDefinition tmpl = (TemplateDefinition)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 tmpl.body.add(0, obj);
                                                   RESULT = tmpl; 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("template",1, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // definition ::= MAP_DEFINITION_TOKEN map definition 
            {
              HashMap<String,Definition> RESULT =null;
		int defleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).left;
		int defright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).right;
		String def = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).value;
		int mapleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int mapright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		MapDefinition map = (MapDefinition)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int defsleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int defsright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		HashMap<String,Definition> defs = (HashMap<String,Definition>)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 map.name = def;
                                                                          defs.put(map.name, map);
                                                                          RESULT = defs; 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("definition",0, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // definition ::= MAP_DEFINITION_TOKEN map 
            {
              HashMap<String,Definition> RESULT =null;
		int defleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int defright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		String def = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int mapleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int mapright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		MapDefinition map = (MapDefinition)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new HashMap<String, Definition>();
                                                                          map.name = def;
                                                                          RESULT.put(map.name, map); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("definition",0, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // definition ::= TEMPLATE_DEFINITION_TOKEN template definition 
            {
              HashMap<String,Definition> RESULT =null;
		int defleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).left;
		int defright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).right;
		String def = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)).value;
		int tmplleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int tmplright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		TemplateDefinition tmpl = (TemplateDefinition)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int defsleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int defsright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		HashMap<String,Definition> defs = (HashMap<String,Definition>)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 tmpl.name = def;
                                                                          defs.put(tmpl.name, tmpl);
                                                                          RESULT = defs; 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("definition",0, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-2)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= definition EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		HashMap<String,Definition> start_val = (HashMap<String,Definition>)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		RESULT = start_val;
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$TemplatingParser$parser.done_parsing();
          return CUP$TemplatingParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // definition ::= TEMPLATE_DEFINITION_TOKEN template 
            {
              HashMap<String,Definition> RESULT =null;
		int defleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).left;
		int defright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).right;
		String def = (String)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)).value;
		int tmplleft = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).left;
		int tmplright = ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()).right;
		TemplateDefinition tmpl = (TemplateDefinition)((java_cup.runtime.Symbol) CUP$TemplatingParser$stack.peek()).value;
		 RESULT = new HashMap<String, Definition>();
                                                                          tmpl.name = def;
                                                                          RESULT.put(tmpl.name, tmpl); 
              CUP$TemplatingParser$result = parser.getSymbolFactory().newSymbol("definition",0, ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.elementAt(CUP$TemplatingParser$top-1)), ((java_cup.runtime.Symbol)CUP$TemplatingParser$stack.peek()), RESULT);
            }
          return CUP$TemplatingParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

