/*
 * Copyright (c) 2014-2023 by Bart Kiers
 *
 * The MIT license.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * Project      : PCRE Parser, an ANTLR 4 grammar for PCRE
 * Developed by : Bart Kiers, bart@big-o.nl
 * Also see     : https://github.com/bkiers/pcre-parser
 *
 * Based on http://www.pcre.org/pcre.txt
 * (REVISION Last updated: 14 June 2021)
 */
lexer grammar PCRELexer;

/// \      general escape character with several uses
BSlash : '\\';

/// $      assert end of string (or line, in multiline mode)
Dollar : '$';

/// .      match any character except newline (by default)
Dot : '.';

/// [      start character class definition
OBrack : '[';

/// ^      assert start of string (or line, in multiline mode)
Caret : '^';

/// |      start of alternative branch
Pipe : '|';

/// ?      extends the meaning of (, also 0 or 1 quantifier.txt, also quantifier.txt minimizer
QMark : '?';

/// *      0 or more quantifier.txt
Star : '*';

/// +      1 or more quantifier.txt, also "possessive quantifier.txt"
Plus : '+';

/// {      start min/max quantifier.txt
OBrace : '{';

CBrace : '}';

/// (      start subpattern
OPar : '(';

/// )      end subpattern
CPar : ')';

/// ]      terminates the character class
CBrack : ']';

OPosixBrack : '[:';
CPosixBrack : ':]';

Comma : ',';
Dash : '-';
UScore : '_';
Eq : '=';
Amp : '&';
Lt : '<';
Gt : '>';
Quote : '\'';
Col : ':';
Hash : '#';
Excl : '!';

Au : 'A';
Bu : 'B';
Cu : 'C';
Du : 'D';
Eu : 'E';
Fu : 'F';
Gu : 'G';
Hu : 'H';
Iu : 'I';
Ju : 'J';
Ku : 'K';
Lu : 'L';
Mu : 'M';
Nu : 'N';
Ou : 'O';
Pu : 'P';
Qu : 'Q';
Ru : 'R';
Su : 'S';
Tu : 'T';
Uu : 'U';
Vu : 'V';
Wu : 'W';
Xu : 'X';
Yu : 'Y';
Zu : 'Z';

Al : 'a';
Bl : 'b';
Cl : 'c';
Dl : 'd';
El : 'e';
Fl : 'f';
Gl : 'g';
Hl : 'h';
Il : 'i';
Jl : 'j';
Kl : 'k';
Ll : 'l';
Ml : 'm';
Nl : 'n';
Ol : 'o';
Pl : 'p';
Ql : 'q';
Rl : 'r';
Sl : 's';
Tl : 't';
Ul : 'u';
Vl : 'v';
Wl : 'w';
Xl : 'x';
Yl : 'y';
Zl : 'z';

D0 : '0';
D1 : '1';
D2 : '2';
D3 : '3';
D4 : '4';
D5 : '5';
D6 : '6';
D7 : '7';
D8 : '8';
D9 : '9';

OTHER
 : .
 ;