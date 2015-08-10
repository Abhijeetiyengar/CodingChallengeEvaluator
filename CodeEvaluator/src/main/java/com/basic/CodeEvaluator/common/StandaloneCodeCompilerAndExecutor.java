package com.basic.CodeEvaluator.common;

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

import com.basic.CodeEvaluator.bean.ParameterBean;
import com.basic.CodeEvaluator.rules.Enricher;

public class StandaloneCodeCompilerAndExecutor {

	// public static void main(String args[]) throws Exception {
	// StandaloneCodeCompilerAndExecutor compilerAndexecutor = new
	// StandaloneCodeCompilerAndExecutor();
	// compilerAndexecutor.compileJavaObject(args[0]);
	//
	// }

	public String compileJavaObject(File newJavaFileName,
			ParameterBean paramBean) throws Exception {
		if (newJavaFileName != null) {
			try {

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
				printDiagnostic(collector);
				String className = fileName.replace(".java", "");
				File classFile = new File(className + ".class");
				String locationToBeCopied = createTmpDirectoryStructure(
						className, paramBean);
				FileUtils.copyFileToDirectory(classFile, new File(
						locationToBeCopied), true);

				FileUtils.deleteQuietly(classFile);

				return className;
			} catch (Exception e) {
				throw e;
			}

		} else {
			throw new RuntimeException("No Java File Name found ");
		}
	}

	private String createTmpDirectoryStructure(String className,
			ParameterBean parameterBean) throws Exception {
		String baseDir = "/tmp/codeEvaluation/"+DateUtil.getCurrentTimeinStringddMMyyyyHHmmss();
		String packageName = parameterBean.getEvaluatedResultsBean()
				.getPackageNameOfJavaClass();
		if (packageName != null && packageName.length() > 0) {
			String[] folderStructure = packageName.split("\\.");
			for (String folder : folderStructure) {
				baseDir = baseDir + "/" + folder;
				FileUtils.forceMkdir(new File(baseDir));

			}
		}

		return baseDir;

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
