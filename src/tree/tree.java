//package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class tree {
	private String treeString;
	
	public String createPathTree(String path){
		this.treeString = createPathTree(path,"");
		return this.treeString;
	}
	
	private String createPathTree(String path,String format){
		// 根据路径名称创建File对象
		File file = new File(path);
		StringBuffer buf = new StringBuffer();
		buf.append(format + file.getName()+"\n");
		// 得到文件名列表
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 利用for-each获取每个File对象
			for (File f : files) {
				if (f.isFile()) {
					buf.append(format+"| " + f.getName()+"\n");
				} else {
					buf.append(createPathTree(""+f,format+"| "));
				}

			}
		}
		return buf.toString();
	}
	
	public void savePathTreeToFile(String filePath){
		
		 try {
	            File file = new File(filePath);
	            PrintStream ps = new PrintStream(new FileOutputStream(file));
	            ps.println(this.treeString);// 往文件里写入字符串
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	public tree() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tree = null;
		tree treeObj = new tree();
		if(args.length>0){
			tree = treeObj.createPathTree(args[0]);
			System.out.println(args[0]);
		}else{
			String path = tree.class.getResource ("").getFile ();
			tree = treeObj.createPathTree(path);
		}
		if(args.length>1){
			treeObj.savePathTreeToFile(args[1]);
		}else if(args.length==1){
			treeObj.savePathTreeToFile(args[0] + "/tree.txt");
		}
		System.out.println(tree);
	}

	public String getTreeString() {
		return treeString;
	}

	public void setTreeString(String treeString) {
		this.treeString = treeString;
	}

}