package melnorme.lang.ide.core;

import org.cfeclipse.ide.core.bundle_model.CfmlBundleModelManager;
import org.cfeclipse.ide.core.engine.CfmlEngineClient;
import org.cfeclipse.ide.core.operations.CfmlBuildManager;
import org.cfeclipse.ide.core.operations.CfmlToolManager;
import melnorme.lang.ide.core.operations.build.BuildManager;
import melnorme.lang.ide.core.project_model.LangBundleModel;

public class LangCore_Actual {
	
	public static final String PLUGIN_ID = "org.cfeclipse.ide.core";
	public static final String NATURE_ID = PLUGIN_ID +".nature";
	
	public static final String BUILDER_ID = PLUGIN_ID + ".Builder";
	public static final String BUILD_PROBLEM_ID = PLUGIN_ID + ".build_problem";
	public static final String SOURCE_PROBLEM_ID = PLUGIN_ID + ".source_problem";
	
	public static final String CfmlNAME = "Lang";
	
	public static CfmlToolManager createToolManagerSingleton() {
		return new CfmlToolManager();
	}
	
	public static CfmlEngineClient createEngineClient() {
		return new CfmlEngineClient();
	}
	
	public static CfmlBundleModelManager createBundleModelManager() {
		return new CfmlBundleModelManager();
	}
	public static CfmlBundleModel getBundleModel() {
		return (CfmlBundleModel) LangCore.getBundleModel();
	}
	public static BuildManager createBuildManager() {
		return new CfmlBuildManager(getBundleModel());
	}
	
	
	public static class CfmlBundleModel extends LangBundleModel {
		
	}
	
}