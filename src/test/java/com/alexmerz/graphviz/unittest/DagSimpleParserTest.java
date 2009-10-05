package com.alexmerz.graphviz.unittest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.alexmerz.graphviz.ParseException;
import com.alexmerz.graphviz.Parser;

public class DagSimpleParserTest {

	@Test
	public void simpleVar() throws ParseException {
        FileReader in=null;
        try {
        	File f = new File( "testfiles/simple-var.dot" );
            in = new FileReader(f);
            Parser p = new Parser(in);            
            assertTrue(p.parse(in));  
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }               
	}

	@Test
	public void simpleProgram() throws ParseException {
        FileReader in=null;
        try {
        	File f = new File( "testfiles/simple-program.dot" );
            in = new FileReader(f);
            Parser p = new Parser(in);            
            assertTrue(p.parse(in));  
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }               
	}
	
	@Test
	public void simpleOperations() throws ParseException {
        FileReader in=null;
        try {
        	File f = new File( "testfiles/simple-operations.dot" );
            in = new FileReader(f);
            Parser p = new Parser(in);            
            assertTrue(p.parse(in));  
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }               
	}

	@Test
	public void simpleNowy() throws ParseException {
        FileReader in=null;
        try {
        	File f = new File( "testfiles/simple-nowy.dot" );
            in = new FileReader(f);
            Parser p = new Parser(in);            
            assertTrue(p.parse(in));  
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }               
	}
	
}
