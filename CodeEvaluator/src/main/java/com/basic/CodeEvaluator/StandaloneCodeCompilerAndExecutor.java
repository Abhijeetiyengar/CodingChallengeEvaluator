package com.basic.CodeEvaluator;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;

import org.apache.commons.io.FileUtils;

public class StandaloneCodeCompilerAndExecutor {



	public static void main(String args[]) throws Exception {
		StandaloneCodeCompilerAndExecutor compilerAndexecutor = new StandaloneCodeCompilerAndExecutor();
		compilerAndexecutor.compileJavaObject(args[0]);

	}

	public String compileJavaObject(String compileFilePath) throws Exception {
		if (compileFilePath != null) {
			try {
				
				File newJavaFileName = new File(compileFilePath);
				String fileName = newJavaFileName.getName();
				System.out.println(fileName);
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				SimpleJavaFileObject fileObject = new InbuiltSampleJavaFileObject(
						newJavaFileName, Kind.SOURCE);
				DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<JavaFileObject>();
				Iterable<? extends JavaFileObject> fileObjectArray = Arrays
						.asList(fileObject);
				 compiler.getTask(null,
						compiler.getStandardFileManager(collector, null, null),
						collector, null, null, fileObjectArray).call();
				String className=fileName.replace(".java", "");
				File classFile = new File(className
						+ ".class");
				FileUtils
						.copyFileToDirectory(classFile, new File("/tmp"), true);

				FileUtils.deleteQuietly(classFile);
				
				printDiagnostic(collector);

				return className;
			} catch (Exception e) {
				throw e;
			}

		} else {
			throw new RuntimeException("No Java File Name found ");
		}
	}

	private void printDiagnostic(DiagnosticCollector<JavaFileObject> diagnostics) {
		for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
				.getDiagnostics()) {
			System.out.println(diagnostic.getCode());
			System.out.println(diagnostic.getKind());
			System.out.println(diagnostic.getPosition());
			System.out.println(diagnostic.getStartPosition());
			System.out.println(diagnostic.getEndPosition());
			System.out.println(diagnostic.getSource());
			System.out.println(diagnostic.getMessage(null));

		}
	}

}

class InbuiltSampleJavaFileObject extends SimpleJavaFileObject {

	File javaFile;

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		return FileUtils.readFileToString(javaFile);

	}

	protected InbuiltSampleJavaFileObject(File javaFile, Kind kind) {
		super(javaFile.toURI(), kind);
		this.javaFile = javaFile;
	}

}
