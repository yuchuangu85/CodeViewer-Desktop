/*
 * 07/27/2009
 *
 * ParseResult.java - The result of a Parser parsing some section of an
 * RSyntaxTextArea.
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package fife.ui.rsyntaxtextarea.parser;

import java.util.List;


/**
 * The result from a {@link Parser}.  This contains the section of lines
 * parsed and any notices for that section.
 *
 * @author Robert Futrell
 * @version 1.0
 * @see DefaultParseResult
 * @see ParserNotice
 */
public interface ParseResult {


	/**
	 * Returns an error that occurred while parsing the document, if any.
	 *
	 * @return The error, or <code>null</code> if the document was
	 *         successfully parsed.
	 */
	Exception getError();


	/**
	 * Returns the first line parsed.  All parser implementations should
	 * currently set this to <code>0</code> and parse the entire document.
	 *
	 * @return The first line parsed.
	 * @see #getLastLineParsed()
	 */
	int getFirstLineParsed();


	/**
	 * Returns the first line parsed.  All parser implementations should
	 * currently set this to the document's line count and parse the entire
	 * document.
	 *
	 * @return The last line parsed.
	 * @see #getFirstLineParsed()
	 */
	int getLastLineParsed();


	/**
	 * Returns the notices for the parsed section.
	 *
	 * @return A list of {@link ParserNotice}s.
	 */
	List<ParserNotice> getNotices();


	/**
	 * Returns the parser that generated these notices.
	 *
	 * @return The parser.
	 */
	Parser getParser();


	/**
	 * Returns the amount of time this parser took to parse the specified
	 * range of text.  This is an optional operation; parsers are permitted
	 * to return <code>0</code> for this value.
	 *
	 * @return The parse time, in milliseconds, or <code>0</code> if the
	 *         parse time was not recorded.
	 */
	long getParseTime();


}
