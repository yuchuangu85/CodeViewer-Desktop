/* The following code was generated by JFlex 1.4.1 on 5/8/21 5:54 PM */

/*
 * 02/15/2005
 *
 * SQLTokenMaker.java - Scanner for SQL.
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * This class generates tokens representing a text stream as SQL.<p>
 *
 * This implementation was created using
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1; however, the generated file
 * was modified for performance.  Memory allocation needs to be almost
 * completely removed to be competitive with the handwritten lexers (subclasses
 * of <code>AbstractTokenMaker</code>, so this class has been modified so that
 * Strings are never allocated (via yytext()), and the scanner never has to
 * worry about refilling its buffer (needlessly copying chars around).
 * We can achieve this because RText always scans exactly 1 line of tokens at a
 * time, and hands the scanner this line as an array of characters (a Segment
 * really).  Since tokens contain pointers to char arrays instead of Strings
 * holding their contents, there is no need for allocating new memory for
 * Strings.<p>
 *
 * The actual algorithm generated for scanning has, of course, not been
 * modified.<p>
 *
 * If you wish to regenerate this file yourself, keep in mind the following:
 * <ul>
 *   <li>The generated <code>SQLTokenMaker.java</code> file will contain two
 *       definitions of both <code>zzRefill</code> and <code>yyreset</code>.
 *       You should hand-delete the second of each definition (the ones
 *       generated by the lexer), as these generated methods modify the input
 *       buffer, which we'll never have to do.</li>
 *   <li>You should also change the declaration/definition of zzBuffer to NOT
 *       be initialized.  This is a needless memory allocation for us since we
 *       will be pointing the array somewhere else anyway.</li>
 *   <li>You should NOT call <code>yylex()</code> on the generated scanner
 *       directly; rather, you should use <code>getTokenList</code> as you would
 *       with any other <code>TokenMaker</code> instance.</li>
 * </ul>
 *
 * @author Robert Futrell
 * @version 0.5
 *
 */

public class SQLTokenMaker extends AbstractJFlexTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** lexical states */
  public static final int STRING = 1;
  public static final int YYINITIAL = 0;
  public static final int MLC = 3;
  public static final int CHAR = 2;

  /**
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED =
    "\11\0\1\4\1\1\25\0\1\4\1\0\1\54\4\0\1\55\2\15"+
    "\1\13\1\11\1\0\1\12\1\17\1\14\1\3\1\50\1\50\1\3"+
    "\1\45\3\3\1\46\1\3\1\16\1\0\1\10\1\7\1\6\2\0"+
    "\1\21\1\37\1\31\1\22\1\20\1\44\1\47\1\41\1\34\1\51"+
    "\1\52\1\23\1\35\1\26\1\33\1\42\1\53\1\25\1\30\1\24"+
    "\1\32\1\36\1\40\1\43\1\27\1\2\1\56\1\0\1\57\1\0"+
    "\1\5\1\0\1\21\1\37\1\31\1\22\1\20\1\44\1\47\1\41"+
    "\1\34\1\51\1\52\1\23\1\35\1\26\1\33\1\42\1\53\1\25"+
    "\1\30\1\24\1\32\1\36\1\40\1\43\1\27\1\2\uff85\0";

  /**
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\1\1\3\1\4\5\5\1\6"+
    "\1\1\1\7\27\1\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\13\1\16\1\17\1\13\1\20\1\13\1\0"+
    "\1\21\1\22\5\1\1\23\46\1\2\23\4\1\1\23"+
    "\7\1\1\23\22\1\1\24\1\13\1\25\1\7\1\0"+
    "\5\1\1\26\51\1\1\23\6\1\1\23\2\1\1\23"+
    "\22\1\1\23\4\1\1\23\4\1\1\23\16\1\1\23"+
    "\41\1\1\23\5\1\1\23\7\1\1\23\6\1\1\23"+
    "\10\1\1\23\41\1\1\23\4\1\1\23\16\1\1\26"+
    "\4\1";

  private static int [] zzUnpackAction() {
    int [] result = new int[361];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\60\0\140\0\220\0\300\0\300\0\360\0\u0120"+
    "\0\u0150\0\u0180\0\300\0\u01b0\0\u01e0\0\u0210\0\300\0\u0240"+
    "\0\u0270\0\u02a0\0\u02d0\0\u0300\0\u0330\0\u0360\0\u0390\0\u03c0"+
    "\0\u03f0\0\u0420\0\u0450\0\u0480\0\u04b0\0\u04e0\0\u0510\0\u0540"+
    "\0\u0570\0\u05a0\0\u05d0\0\u0600\0\u0630\0\u0660\0\u0690\0\u06c0"+
    "\0\300\0\300\0\u06f0\0\u0720\0\300\0\u0750\0\u0780\0\300"+
    "\0\u07b0\0\u07e0\0\300\0\u0810\0\u0840\0\u0870\0\300\0\u08a0"+
    "\0\u08d0\0\u0900\0\u0930\0\u0960\0\u0990\0\u09c0\0\u09f0\0\u0a20"+
    "\0\u0a50\0\u0a80\0\u0ab0\0\u0ae0\0\u0b10\0\u0b40\0\u0b70\0\u0ba0"+
    "\0\u0bd0\0\u0c00\0\u0c30\0\u0c60\0\u0c90\0\u0cc0\0\u0cf0\0\u0d20"+
    "\0\u0d50\0\u0d80\0\u0db0\0\u0de0\0\u0e10\0\u0e40\0\u0e70\0\u0ea0"+
    "\0\u0ed0\0\u0f00\0\u0f30\0\u0f60\0\u0f90\0\u0fc0\0\u0ff0\0\u1020"+
    "\0\u1050\0\u1080\0\u10b0\0\u10e0\0\360\0\u1110\0\u1140\0\u1170"+
    "\0\u11a0\0\u11d0\0\u1200\0\u1230\0\u1260\0\u1290\0\u12c0\0\u12f0"+
    "\0\u1320\0\u1350\0\u1380\0\u13b0\0\u13e0\0\u1410\0\u1440\0\u1470"+
    "\0\u14a0\0\u14d0\0\u1500\0\u1530\0\u1560\0\u1590\0\u15c0\0\u15f0"+
    "\0\u1620\0\u1650\0\u1680\0\u16b0\0\300\0\300\0\300\0\u16e0"+
    "\0\u16e0\0\u1710\0\u1740\0\u1770\0\u17a0\0\u17d0\0\360\0\u1800"+
    "\0\u0990\0\u1830\0\u1860\0\u1890\0\u18c0\0\u18f0\0\u1920\0\u1950"+
    "\0\u1980\0\u19b0\0\u19e0\0\u1a10\0\u1a40\0\u1a70\0\u1aa0\0\u1ad0"+
    "\0\u1b00\0\u1b30\0\u1b60\0\u1b90\0\u1bc0\0\u1bf0\0\u1c20\0\u1c50"+
    "\0\u1c80\0\u1cb0\0\u1ce0\0\u1d10\0\u1d40\0\u1d70\0\u1da0\0\u1dd0"+
    "\0\u1e00\0\u1e30\0\u1e60\0\u1e90\0\u1ec0\0\u1ef0\0\u1f20\0\u1f50"+
    "\0\u1f80\0\u1fb0\0\u1fe0\0\u2010\0\u2040\0\u2070\0\u20a0\0\u20d0"+
    "\0\u2100\0\u2130\0\u2160\0\u2190\0\u21c0\0\u21f0\0\u2220\0\u2250"+
    "\0\u2280\0\u22b0\0\u22e0\0\u2310\0\u2340\0\u2370\0\u23a0\0\u23d0"+
    "\0\u2400\0\u2430\0\u2460\0\u2490\0\u1350\0\u24c0\0\u24f0\0\u2520"+
    "\0\u2550\0\u2580\0\u25b0\0\u25e0\0\u2610\0\u2640\0\u2670\0\u26a0"+
    "\0\u26d0\0\u2700\0\u2730\0\u2760\0\u2790\0\u27c0\0\u27f0\0\u2820"+
    "\0\u2850\0\u2880\0\u28b0\0\u28e0\0\u2910\0\u2940\0\u2970\0\u29a0"+
    "\0\u29d0\0\u2a00\0\u2a30\0\u2a60\0\u2a90\0\u2ac0\0\u2af0\0\u2b20"+
    "\0\u2b50\0\u2b80\0\u2bb0\0\u2be0\0\u2c10\0\u2c40\0\u2c70\0\u2ca0"+
    "\0\u2cd0\0\u2d00\0\u2d30\0\u2d60\0\u2d90\0\u2dc0\0\u2df0\0\u2e20"+
    "\0\u2e50\0\u2e80\0\u2eb0\0\u2ee0\0\u2f10\0\u2f40\0\u2f70\0\u2fa0"+
    "\0\u1650\0\u2fd0\0\u3000\0\u3030\0\u3060\0\u3090\0\u0c60\0\u30c0"+
    "\0\u30f0\0\u3120\0\u3150\0\u3180\0\u31b0\0\u31e0\0\u17a0\0\u3210"+
    "\0\u3240\0\u2160\0\u3270\0\u32a0\0\u32d0\0\u2e50\0\u3300\0\u3330"+
    "\0\u3360\0\u3390\0\u33c0\0\u33f0\0\u3420\0\u3450\0\u3480\0\u34b0"+
    "\0\u34e0\0\u3510\0\u3540\0\u3570\0\u35a0\0\u35d0\0\u3600\0\u3630"+
    "\0\u3660\0\u3690\0\u36c0\0\u36f0\0\u3720\0\u3750\0\u3780\0\u37b0"+
    "\0\u37e0\0\u3810\0\u3840\0\u3870\0\u38a0\0\u38d0\0\u3900\0\u3930"+
    "\0\u3960\0\u3990\0\u39c0\0\u39f0\0\u3a20\0\u3a50\0\u3a80\0\u3ab0"+
    "\0\u3ae0\0\u3b10\0\u3b40\0\u3b70\0\u3ba0\0\u3bd0\0\u3c00\0\u3c30"+
    "\0\u3c60\0\u3c90\0\u3cc0\0\u3cf0\0\u3d20\0\u3d50\0\u3d80\0\u3db0"+
    "\0\u3de0\0\u3e10\0\u3e40\0\u3e70\0\u3ea0\0\u3ed0\0\u3f00\0\u3f30"+
    "\0\u3f60";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[361];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\10\1\11\1\5\1\12\1\13"+
    "\1\14\1\13\1\15\1\13\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\7\1\45\2\10\1\46\1\10"+
    "\1\47\1\50\1\7\1\51\1\52\1\53\1\5\1\54"+
    "\1\55\52\54\1\56\3\54\1\57\1\60\53\57\1\61"+
    "\2\57\1\62\1\63\11\62\1\64\44\62\62\0\2\7"+
    "\1\0\1\7\12\0\34\7\7\0\1\10\13\0\1\21"+
    "\1\65\24\0\2\10\1\0\1\10\13\0\1\11\62\0"+
    "\1\13\56\0\2\13\62\0\1\66\60\0\1\67\46\0"+
    "\1\7\15\0\25\7\2\0\1\7\1\0\3\7\7\0"+
    "\1\21\14\0\1\65\24\0\2\21\1\0\1\21\11\0"+
    "\2\7\1\0\1\7\12\0\3\7\1\70\2\7\1\71"+
    "\14\7\1\72\10\7\6\0\2\7\1\0\1\7\12\0"+
    "\2\7\1\71\1\73\2\7\1\74\1\7\1\75\1\7"+
    "\1\76\3\7\1\77\15\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\100\1\101\3\7\1\102\5\7\1\103\1\104"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\1\105\12\7"+
    "\1\106\1\107\17\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\110\1\111\3\7\1\112\1\7\1\113\3\7\1\114"+
    "\1\115\4\7\1\116\12\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\117\13\7\1\120\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\12\7\1\121\1\122\20\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\123\33\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\124\3\7\1\125\2\7\1\126\2\7"+
    "\1\127\1\130\1\131\1\132\3\7\1\133\12\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\7\1\70\3\7\1\134"+
    "\4\7\1\135\1\136\5\7\1\137\12\7\6\0\2\7"+
    "\1\0\1\7\12\0\6\7\1\140\1\7\1\141\11\7"+
    "\1\142\11\7\6\0\2\7\1\0\1\7\12\0\3\7"+
    "\1\143\1\7\1\144\1\145\2\7\1\146\1\147\5\7"+
    "\1\150\1\7\1\151\11\7\6\0\2\7\1\0\1\7"+
    "\12\0\6\7\1\152\1\7\1\145\4\7\1\114\11\7"+
    "\1\153\4\7\6\0\2\7\1\0\1\7\12\0\1\154"+
    "\1\155\11\7\1\156\1\157\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\7\1\160\32\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\161\6\7\1\162\3\7\1\163\1\164"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\14\7\1\165"+
    "\4\7\1\166\12\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\7\1\167\32\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\170\1\171\3\7\1\172\5\7\1\173\1\174\17\7"+
    "\6\0\2\7\1\0\1\7\12\0\3\7\1\175\1\7"+
    "\1\176\5\7\1\177\20\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\200\4\7\1\201\4\7\1\202\21\7\6\0"+
    "\2\7\1\0\1\7\12\0\13\7\1\203\20\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\204\33\7\4\0\57\53"+
    "\1\205\1\54\1\0\52\54\1\0\3\54\54\0\1\206"+
    "\3\0\1\57\1\0\53\57\1\0\2\57\55\0\1\206"+
    "\2\0\1\62\1\0\11\62\1\0\44\62\14\0\1\207"+
    "\46\0\1\210\5\0\2\211\32\0\2\210\1\0\1\210"+
    "\7\0\1\66\1\0\56\66\2\0\2\7\1\0\1\7"+
    "\12\0\10\7\1\212\23\7\6\0\2\7\1\0\1\7"+
    "\12\0\2\7\1\145\31\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\213\7\7\1\214\17\7\6\0\2\7"+
    "\1\0\1\7\12\0\3\7\1\145\1\215\27\7\6\0"+
    "\2\7\1\0\1\7\12\0\2\7\1\145\4\7\1\145"+
    "\24\7\6\0\2\7\1\0\1\7\12\0\11\7\1\145"+
    "\22\7\6\0\2\7\1\0\1\7\12\0\4\7\1\216"+
    "\27\7\6\0\2\7\1\0\1\7\12\0\1\7\1\145"+
    "\25\7\1\217\4\7\6\0\2\7\1\0\1\7\12\0"+
    "\3\7\1\220\4\7\1\221\23\7\6\0\2\7\1\0"+
    "\1\7\12\0\4\7\1\222\27\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\114\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\12\7\1\223\21\7\6\0\2\7\1\0"+
    "\1\7\12\0\10\7\1\224\23\7\6\0\2\7\1\0"+
    "\1\7\12\0\16\7\1\225\5\7\1\122\7\7\6\0"+
    "\2\7\1\0\1\7\12\0\6\7\1\226\11\7\1\227"+
    "\6\7\1\230\4\7\6\0\2\7\1\0\1\7\12\0"+
    "\32\7\1\212\1\7\6\0\2\7\1\0\1\7\12\0"+
    "\23\7\1\122\10\7\6\0\2\7\1\0\1\7\12\0"+
    "\17\7\1\231\14\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\7\1\232\12\7\1\233\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\22\7\1\212\11\7\6\0\2\7\1\0"+
    "\1\7\12\0\22\7\1\145\11\7\6\0\2\7\1\0"+
    "\1\7\12\0\15\7\1\234\16\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\157\33\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\7\1\235\22\7\1\236\7\7\6\0\2\7"+
    "\1\0\1\7\12\0\27\7\1\237\4\7\6\0\2\7"+
    "\1\0\1\7\12\0\3\7\1\235\11\7\1\240\16\7"+
    "\6\0\2\7\1\0\1\7\12\0\4\7\1\145\27\7"+
    "\6\0\2\7\1\0\1\7\12\0\10\7\1\241\23\7"+
    "\6\0\2\7\1\0\1\7\12\0\3\7\1\242\1\145"+
    "\3\7\1\243\23\7\6\0\2\7\1\0\1\7\12\0"+
    "\2\7\1\244\2\7\1\245\26\7\6\0\2\7\1\0"+
    "\1\7\12\0\10\7\1\246\23\7\6\0\2\7\1\0"+
    "\1\7\12\0\15\7\1\145\1\7\1\247\14\7\6\0"+
    "\2\7\1\0\1\7\12\0\15\7\1\212\16\7\6\0"+
    "\2\7\1\0\1\7\12\0\6\7\1\250\25\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\7\1\251\32\7\6\0"+
    "\2\7\1\0\1\7\12\0\13\7\1\252\20\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\253\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\5\7\1\254\26\7\6\0\2\7"+
    "\1\0\1\7\12\0\3\7\1\255\2\7\1\256\3\7"+
    "\1\257\21\7\6\0\2\7\1\0\1\7\12\0\1\7"+
    "\1\260\32\7\6\0\2\7\1\0\1\7\12\0\14\7"+
    "\1\261\17\7\6\0\2\7\1\0\1\7\12\0\1\262"+
    "\13\7\1\263\17\7\6\0\2\7\1\0\1\7\12\0"+
    "\2\7\1\253\17\7\1\227\11\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\264\33\7\6\0\2\7\1\0\1\7"+
    "\12\0\2\7\1\215\31\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\265\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\215\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\6\7\1\266\25\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\267\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\2\7\1\270\1\7\1\271\1\7\1\215\1\7"+
    "\1\272\23\7\6\0\2\7\1\0\1\7\12\0\6\7"+
    "\1\273\25\7\6\0\2\7\1\0\1\7\12\0\5\7"+
    "\1\274\7\7\1\275\16\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\276\16\7\1\145\10\7\6\0\2\7"+
    "\1\0\1\7\12\0\2\7\1\145\3\7\1\50\25\7"+
    "\6\0\2\7\1\0\1\7\12\0\6\7\1\145\25\7"+
    "\6\0\2\7\1\0\1\7\12\0\3\7\1\277\1\7"+
    "\1\300\26\7\6\0\2\7\1\0\1\7\12\0\4\7"+
    "\1\301\27\7\6\0\2\7\1\0\1\7\12\0\4\7"+
    "\1\212\27\7\6\0\2\7\1\0\1\7\12\0\13\7"+
    "\1\302\20\7\6\0\2\7\1\0\1\7\12\0\4\7"+
    "\1\303\1\7\1\304\25\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\305\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\306\33\7\6\0\2\7\1\0\1\7\12\0"+
    "\16\7\1\245\15\7\6\0\2\7\1\0\1\7\12\0"+
    "\5\7\1\307\26\7\6\0\2\7\1\0\1\7\12\0"+
    "\5\7\1\310\2\7\1\311\23\7\6\0\2\7\1\0"+
    "\1\7\12\0\14\7\1\312\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\10\7\1\313\23\7\6\0\2\7\1\0"+
    "\1\7\12\0\16\7\1\314\15\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\315\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\316\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\5\7\1\317\26\7\6\0\2\7\1\0"+
    "\1\7\12\0\6\7\1\320\25\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\321\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\14\7\1\71\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\14\7\1\157\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\7\7\1\145\24\7\7\0\1\210\41\0"+
    "\2\210\1\0\1\210\11\0\2\7\1\0\1\7\12\0"+
    "\1\145\33\7\6\0\2\7\1\0\1\7\12\0\5\7"+
    "\1\322\26\7\6\0\2\7\1\0\1\7\12\0\10\7"+
    "\1\323\23\7\6\0\2\7\1\0\1\7\12\0\1\262"+
    "\33\7\6\0\2\7\1\0\1\7\12\0\13\7\1\324"+
    "\20\7\6\0\2\7\1\0\1\7\12\0\1\325\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\326\1\327\32\7"+
    "\6\0\2\7\1\0\1\7\12\0\17\7\1\330\14\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\7\1\331\2\7"+
    "\1\332\27\7\6\0\2\7\1\0\1\7\12\0\1\235"+
    "\33\7\6\0\2\7\1\0\1\7\12\0\27\7\1\333"+
    "\4\7\6\0\2\7\1\0\1\7\12\0\1\334\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\14\7\1\335\17\7"+
    "\6\0\2\7\1\0\1\7\12\0\3\7\1\336\30\7"+
    "\6\0\2\7\1\0\1\7\12\0\6\7\1\337\25\7"+
    "\6\0\2\7\1\0\1\7\12\0\15\7\1\217\16\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\340\33\7\6\0"+
    "\2\7\1\0\1\7\12\0\3\7\1\145\30\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\341\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\21\7\1\122\12\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\342\16\7\1\215\14\7\6\0"+
    "\2\7\1\0\1\7\12\0\6\7\1\275\25\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\343\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\10\7\1\344\23\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\345\33\7\6\0\2\7\1\0"+
    "\1\7\12\0\14\7\1\263\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\4\7\1\346\27\7\6\0\2\7\1\0"+
    "\1\7\12\0\10\7\1\347\23\7\6\0\2\7\1\0"+
    "\1\7\12\0\27\7\1\330\4\7\6\0\2\7\1\0"+
    "\1\7\12\0\3\7\1\350\30\7\6\0\2\7\1\0"+
    "\1\7\12\0\5\7\1\122\26\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\7\1\325\32\7\6\0\2\7\1\0"+
    "\1\7\12\0\5\7\1\351\26\7\6\0\2\7\1\0"+
    "\1\7\12\0\12\7\1\352\21\7\6\0\2\7\1\0"+
    "\1\7\12\0\10\7\1\353\1\354\4\7\1\355\15\7"+
    "\6\0\2\7\1\0\1\7\12\0\6\7\1\356\25\7"+
    "\6\0\2\7\1\0\1\7\12\0\5\7\1\357\26\7"+
    "\6\0\2\7\1\0\1\7\12\0\13\7\1\157\17\7"+
    "\1\360\6\0\2\7\1\0\1\7\12\0\5\7\1\145"+
    "\26\7\6\0\2\7\1\0\1\7\12\0\6\7\1\361"+
    "\25\7\6\0\2\7\1\0\1\7\12\0\13\7\1\362"+
    "\20\7\6\0\2\7\1\0\1\7\12\0\1\363\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\364\33\7\6\0"+
    "\2\7\1\0\1\7\12\0\14\7\1\365\17\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\366\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\367\12\7\1\145\20\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\252\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\13\7\1\370\20\7\6\0\2\7"+
    "\1\0\1\7\12\0\27\7\1\212\4\7\6\0\2\7"+
    "\1\0\1\7\12\0\13\7\1\145\20\7\6\0\2\7"+
    "\1\0\1\7\12\0\11\7\1\371\22\7\6\0\2\7"+
    "\1\0\1\7\12\0\12\7\1\372\21\7\6\0\2\7"+
    "\1\0\1\7\12\0\11\7\1\373\5\7\1\374\2\7"+
    "\1\145\11\7\6\0\2\7\1\0\1\7\12\0\20\7"+
    "\1\375\13\7\6\0\2\7\1\0\1\7\12\0\3\7"+
    "\1\376\30\7\6\0\2\7\1\0\1\377\12\0\34\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\7\1\u0100\32\7"+
    "\6\0\2\7\1\0\1\7\12\0\21\7\1\145\12\7"+
    "\6\0\2\7\1\0\1\7\12\0\5\7\1\212\1\145"+
    "\25\7\6\0\2\7\1\0\1\7\12\0\11\7\1\u0101"+
    "\22\7\6\0\2\7\1\0\1\7\12\0\1\7\1\u0102"+
    "\32\7\6\0\2\7\1\0\1\7\12\0\10\7\1\u0103"+
    "\23\7\6\0\2\7\1\0\1\7\12\0\15\7\1\304"+
    "\16\7\6\0\2\7\1\0\1\7\12\0\14\7\1\u0104"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\13\7\1\122"+
    "\20\7\6\0\2\7\1\0\1\7\12\0\1\7\1\u0105"+
    "\32\7\6\0\2\7\1\0\1\7\12\0\15\7\1\145"+
    "\16\7\6\0\2\7\1\0\1\7\12\0\1\u0106\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\u0107\33\7\6\0"+
    "\2\7\1\0\1\7\12\0\12\7\1\114\21\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\7\1\u0108\32\7\6\0"+
    "\2\7\1\0\1\7\12\0\4\7\1\u0109\27\7\6\0"+
    "\2\7\1\0\1\7\12\0\14\7\1\u010a\17\7\6\0"+
    "\2\7\1\0\1\7\12\0\4\7\1\u010b\27\7\6\0"+
    "\2\7\1\0\1\7\12\0\17\7\1\u010c\14\7\6\0"+
    "\2\7\1\0\1\7\12\0\3\7\1\212\30\7\6\0"+
    "\2\7\1\0\1\7\12\0\3\7\1\u010d\30\7\6\0"+
    "\2\7\1\0\1\7\12\0\14\7\1\u010e\17\7\6\0"+
    "\2\7\1\0\1\7\12\0\4\7\1\u010f\12\7\1\374"+
    "\14\7\6\0\2\7\1\0\1\7\12\0\5\7\1\217"+
    "\26\7\6\0\2\7\1\0\1\7\12\0\11\7\1\u0110"+
    "\22\7\6\0\2\7\1\0\1\7\12\0\1\u0111\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\10\7\1\u0112\23\7"+
    "\6\0\2\7\1\0\1\7\12\0\10\7\1\u0113\23\7"+
    "\6\0\2\7\1\0\1\7\12\0\5\7\1\u0114\26\7"+
    "\6\0\2\7\1\0\1\7\12\0\5\7\1\u0115\26\7"+
    "\6\0\2\7\1\0\1\7\12\0\11\7\1\122\22\7"+
    "\6\0\2\7\1\0\1\7\12\0\14\7\1\u0116\17\7"+
    "\6\0\2\7\1\0\1\7\12\0\16\7\1\u0117\15\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\u0118\33\7\6\0"+
    "\2\7\1\0\1\7\12\0\4\7\1\u0119\27\7\6\0"+
    "\2\7\1\0\1\7\12\0\3\7\1\u011a\30\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\u011b\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\15\7\1\157\16\7\6\0\2\7"+
    "\1\0\1\7\12\0\4\7\1\u011c\27\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\7\1\u011d\32\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\u011e\33\7\6\0\2\7\1\0"+
    "\1\7\12\0\4\7\1\u011f\27\7\6\0\2\7\1\0"+
    "\1\377\12\0\1\7\1\u0120\32\7\6\0\2\7\1\0"+
    "\1\7\12\0\12\7\1\212\21\7\6\0\2\7\1\0"+
    "\1\7\12\0\27\7\1\145\4\7\6\0\2\7\1\0"+
    "\1\7\12\0\17\7\1\u0121\14\7\6\0\2\7\1\0"+
    "\1\7\12\0\4\7\1\u0122\27\7\6\0\2\7\1\0"+
    "\1\7\12\0\5\7\1\u0123\26\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\157\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\23\7\1\145\10\7\6\0\2\7\1\0"+
    "\1\7\12\0\27\7\1\u0124\4\7\6\0\2\7\1\0"+
    "\1\7\12\0\5\7\1\212\26\7\6\0\2\7\1\0"+
    "\1\7\12\0\21\7\1\u0125\12\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\u0126\33\7\6\0\2\7\1\0\1\7"+
    "\12\0\21\7\1\u0127\12\7\6\0\2\7\1\0\1\7"+
    "\12\0\14\7\1\u0128\17\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\116\33\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\u0129\33\7\6\0\2\7\1\0\1\7\12\0\3\7"+
    "\1\u012a\30\7\6\0\2\7\1\0\1\7\12\0\5\7"+
    "\1\204\26\7\6\0\2\7\1\0\1\7\12\0\1\u012b"+
    "\33\7\6\0\2\7\1\0\1\7\12\0\15\7\1\u012c"+
    "\16\7\6\0\2\7\1\0\1\7\12\0\20\7\1\u012d"+
    "\13\7\6\0\2\7\1\0\1\7\12\0\4\7\1\u012e"+
    "\27\7\6\0\2\7\1\0\1\7\12\0\4\7\1\u012f"+
    "\27\7\6\0\2\7\1\0\1\7\12\0\14\7\1\u0130"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\5\7\1\u0110"+
    "\26\7\6\0\2\7\1\0\1\7\12\0\11\7\1\u0131"+
    "\22\7\6\0\2\7\1\0\1\7\12\0\10\7\1\145"+
    "\23\7\6\0\2\7\1\0\1\7\12\0\6\7\1\u0132"+
    "\25\7\6\0\2\7\1\0\1\7\12\0\14\7\1\130"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\1\7\1\70"+
    "\32\7\6\0\2\7\1\0\1\7\12\0\3\7\1\u0133"+
    "\30\7\6\0\2\7\1\0\1\7\12\0\6\7\1\u0134"+
    "\25\7\6\0\2\7\1\0\1\7\12\0\1\110\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\7\1\235\32\7"+
    "\6\0\2\7\1\0\1\7\12\0\3\7\1\u0135\20\7"+
    "\1\u0136\7\7\6\0\2\7\1\0\1\7\12\0\4\7"+
    "\1\u0137\27\7\6\0\2\7\1\0\1\7\12\0\1\u0138"+
    "\33\7\6\0\2\7\1\0\1\7\12\0\14\7\1\221"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\13\7\1\u0139"+
    "\20\7\6\0\2\7\1\0\1\7\12\0\15\7\1\u013a"+
    "\16\7\6\0\2\7\1\0\1\7\12\0\5\7\1\u013b"+
    "\26\7\6\0\2\7\1\0\1\7\12\0\14\7\1\u012b"+
    "\17\7\6\0\2\7\1\0\1\7\12\0\6\7\1\u013c"+
    "\25\7\6\0\2\7\1\0\1\7\12\0\5\7\1\u013d"+
    "\26\7\6\0\2\7\1\0\1\7\12\0\4\7\1\u013e"+
    "\27\7\6\0\2\7\1\0\1\7\12\0\5\7\1\u0131"+
    "\26\7\6\0\2\7\1\0\1\7\12\0\11\7\1\147"+
    "\22\7\6\0\2\7\1\0\1\7\12\0\31\7\1\242"+
    "\2\7\6\0\2\7\1\0\1\7\12\0\1\7\1\u013f"+
    "\32\7\6\0\2\7\1\0\1\7\12\0\1\u0140\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\1\71\33\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\7\1\262\32\7\6\0"+
    "\2\7\1\0\1\7\12\0\6\7\1\304\25\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\7\1\157\32\7\6\0"+
    "\2\7\1\0\1\7\12\0\1\u0141\33\7\6\0\2\7"+
    "\1\0\1\7\12\0\6\7\1\122\25\7\6\0\2\7"+
    "\1\0\1\7\12\0\1\u0142\33\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\u0143\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\14\7\1\u0144\17\7\6\0\2\7\1\0"+
    "\1\7\12\0\25\7\2\145\5\7\6\0\2\7\1\0"+
    "\1\7\12\0\27\7\1\157\4\7\6\0\2\7\1\0"+
    "\1\7\12\0\4\7\1\217\27\7\6\0\2\7\1\0"+
    "\1\7\12\0\11\7\1\u0145\22\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\u0146\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\11\7\1\u0147\22\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\7\1\u0148\32\7\6\0\2\7\1\0"+
    "\1\7\12\0\13\7\1\u0149\20\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\7\1\u014a\32\7\6\0\2\7\1\0"+
    "\1\7\12\0\6\7\1\u014b\25\7\6\0\2\7\1\0"+
    "\1\7\12\0\6\7\1\u013a\25\7\6\0\2\7\1\0"+
    "\1\u014c\12\0\34\7\6\0\2\7\1\0\1\7\12\0"+
    "\14\7\1\u014d\17\7\6\0\2\7\1\0\1\7\12\0"+
    "\4\7\1\u014e\4\7\1\204\22\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\7\1\u011a\32\7\6\0\2\7\1\0"+
    "\1\7\12\0\1\u014f\33\7\6\0\2\7\1\0\1\7"+
    "\12\0\11\7\1\u0150\22\7\6\0\2\7\1\0\1\7"+
    "\12\0\5\7\1\u0151\26\7\6\0\2\7\1\0\1\7"+
    "\12\0\6\7\1\u0152\25\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\u0153\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\5\7\1\71\26\7\6\0\2\7\1\0\1\7"+
    "\12\0\13\7\1\u0154\20\7\6\0\2\7\1\0\1\7"+
    "\12\0\5\7\1\u0155\26\7\6\0\2\7\1\0\1\7"+
    "\12\0\20\7\1\145\13\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\u0156\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\4\7\1\u0157\27\7\6\0\2\7\1\0\1\7"+
    "\12\0\5\7\1\316\26\7\6\0\2\7\1\0\1\7"+
    "\12\0\15\7\1\114\16\7\6\0\2\7\1\0\1\7"+
    "\12\0\11\7\1\u0158\22\7\6\0\2\7\1\0\1\7"+
    "\12\0\12\7\1\u0159\21\7\6\0\2\7\1\0\1\7"+
    "\12\0\6\7\1\u015a\25\7\6\0\2\7\1\0\1\u015b"+
    "\12\0\34\7\6\0\2\7\1\0\1\7\12\0\6\7"+
    "\1\u0135\25\7\6\0\2\7\1\0\1\7\12\0\11\7"+
    "\1\u015c\22\7\6\0\2\7\1\0\1\7\12\0\25\7"+
    "\1\145\2\7\1\145\3\7\6\0\2\7\1\0\1\7"+
    "\12\0\27\7\1\u015d\4\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\u015e\33\7\6\0\2\7\1\0\1\7\12\0"+
    "\6\7\1\217\25\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\u015f\33\7\6\0\2\7\1\0\1\7\12\0\5\7"+
    "\1\u0133\26\7\6\0\2\7\1\0\1\7\12\0\1\217"+
    "\33\7\6\0\2\7\1\0\1\7\12\0\1\u0109\33\7"+
    "\6\0\2\7\1\0\1\7\12\0\10\7\1\227\23\7"+
    "\6\0\2\7\1\0\1\7\12\0\27\7\1\217\4\7"+
    "\6\0\2\7\1\0\1\7\12\0\2\7\1\u0135\1\7"+
    "\1\u0160\5\7\1\u0159\21\7\6\0\2\7\1\0\1\7"+
    "\12\0\1\u0161\33\7\6\0\2\7\1\0\1\7\12\0"+
    "\4\7\1\u0162\27\7\6\0\2\7\1\0\1\7\12\0"+
    "\5\7\1\u0109\26\7\6\0\2\7\1\0\1\7\12\0"+
    "\15\7\1\u0101\16\7\6\0\2\7\1\0\1\7\12\0"+
    "\14\7\1\u0163\17\7\6\0\2\7\1\0\1\7\12\0"+
    "\10\7\1\u0109\23\7\6\0\2\7\1\0\1\7\12\0"+
    "\21\7\1\217\12\7\6\0\2\7\1\0\1\7\12\0"+
    "\15\7\1\u0164\16\7\6\0\2\7\1\0\1\7\12\0"+
    "\1\u0165\33\7\6\0\2\7\1\0\1\7\12\0\10\7"+
    "\1\u0166\23\7\6\0\2\7\1\0\1\7\12\0\4\7"+
    "\1\u0167\27\7\6\0\2\7\1\0\1\7\12\0\1\7"+
    "\1\u0168\32\7\6\0\2\7\1\0\1\7\12\0\15\7"+
    "\1\u0169\16\7\6\0\2\7\1\0\1\7\12\0\22\7"+
    "\1\217\11\7\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[16272];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\2\11\4\1\1\11\3\1\1\11\31\1\2\11"+
    "\2\1\1\11\2\1\1\11\2\1\1\11\1\1\1\0"+
    "\1\1\1\11\115\1\3\11\1\1\1\0\340\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[361];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */


	/**
	 * Constructor.  This must be here because JFlex does not generate a
	 * no-parameter constructor.
	 */
	public SQLTokenMaker() {
		super();
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos-1, tokenType);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array The character array.
	 * @param start The starting offset in the array.
	 * @param end The ending offset in the array.
	 * @param tokenType The token's type.
	 * @param startOffset The offset in the document at which this token
	 *                    occurs.
	 */
	@Override
	public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
		super.addToken(array, start,end, tokenType, startOffset);
		zzStartRead = zzMarkedPos;
	}


	/**
	 * Overridden to return <code>true</code> so paren matching occurs for
	 * SQL.
	 *
	 * @return <code>true</code> always.
	 */
	@Override
	public boolean getCurlyBracesDenoteCodeBlocks(int languageIndex) {
		return true;
	}


	@Override
	public String[] getLineCommentStartAndEnd(int languageIndex) {
		return new String[] { "--", null };
	}


	@Override
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = YYINITIAL;
		switch (initialTokenType) {
			case Token.LITERAL_STRING_DOUBLE_QUOTE:
				state = STRING;
				start = text.offset;
				break;
			case Token.LITERAL_CHAR:
				state = CHAR;
				start = text.offset;
				break;
			case Token.COMMENT_MULTILINE:
				state = MLC;
				start = text.offset;
				break;
			default:
				state = YYINITIAL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new TokenImpl();
		}

	}


	/**
	 * Refills the input buffer.
	 *
	 * @return      <code>true</code> if EOF was reached, otherwise
	 *              <code>false</code>.
	 */
	private boolean zzRefill() {
		return zzCurrentPos>=s.offset+s.count;
	}


	/**
	 * Resets the scanner to read from a new input stream.
	 * Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream
	 * <b>cannot</b> be reused (internal buffer is discarded and lost).
	 * Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader   the new input stream
	 */
	public final void yyreset(Reader reader) {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill
		 * no longer "refills" the buffer (since the way we do it, it's always
		 * "full" the first time through, since it points to the segment's
		 * array).  So, we assign zzEndRead here.
		 */
		//zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtEOF  = false;
	}




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public SQLTokenMaker(Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public SQLTokenMaker(InputStream in) {
    this(new InputStreamReader(in));
  }

  /**
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 178) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Closes the input stream.
   */
  public final void yyclose() throws IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   IOException  if any I/O-Error occurs
   */
  public Token yylex() throws IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = zzLexicalState;


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 20:
          { addToken(Token.PREPROCESSOR);
          }
        case 23: break;
        case 2:
          { addNullToken(); return firstToken;
          }
        case 24: break;
        case 21:
          { yybegin(YYINITIAL); addToken(start,zzStartRead+1, Token.COMMENT_MULTILINE);
          }
        case 25: break;
        case 18:
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 26: break;
        case 4:
          { addToken(Token.WHITESPACE);
          }
        case 27: break;
        case 14:
          { addToken(start,zzStartRead-1, Token.LITERAL_CHAR); return firstToken;
          }
        case 28: break;
        case 9:
          { start = zzMarkedPos-1; yybegin(CHAR);
          }
        case 29: break;
        case 7:
          { addToken(Token.LITERAL_NUMBER_FLOAT);
          }
        case 30: break;
        case 19:
          { addToken(Token.RESERVED_WORD);
          }
        case 31: break;
        case 6:
          { addToken(Token.SEPARATOR);
          }
        case 32: break;
        case 15:
          { yybegin(YYINITIAL); addToken(start,zzStartRead, Token.LITERAL_CHAR);
          }
        case 33: break;
        case 1:
          { addToken(Token.IDENTIFIER);
          }
        case 34: break;
        case 22:
          { addToken(Token.FUNCTION);
          }
        case 35: break;
        case 12:
          { addToken(start,zzStartRead-1, Token.LITERAL_STRING_DOUBLE_QUOTE); return firstToken;
          }
        case 36: break;
        case 17:
          { addToken(Token.COMMENT_EOL);
          }
        case 37: break;
        case 8:
          { start = zzMarkedPos-1; yybegin(STRING);
          }
        case 38: break;
        case 3:
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 39: break;
        case 5:
          { addToken(Token.OPERATOR);
          }
        case 40: break;
        case 13:
          { yybegin(YYINITIAL); addToken(start,zzStartRead, Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 41: break;
        case 10:
          { addToken(Token.ERROR_IDENTIFIER); addNullToken(); return firstToken;
          }
        case 42: break;
        case 11:
          {
          }
        case 43: break;
        case 16:
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 44: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case STRING: {
              addToken(start,zzStartRead-1, Token.LITERAL_STRING_DOUBLE_QUOTE); return firstToken;
            }
            case 362: break;
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 363: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 364: break;
            case CHAR: {
              addToken(start,zzStartRead-1, Token.LITERAL_CHAR); return firstToken;
            }
            case 365: break;
            default:
            return null;
            }
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
