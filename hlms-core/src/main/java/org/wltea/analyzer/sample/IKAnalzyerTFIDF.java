/**
 * IK 中文分词  版本 5.0.1
 * IK Analyzer release 5.0.1
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package org.wltea.analyzer.sample;

import java.io.IOException;
import java.io.StringReader;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;


import com.hl.ik.entity.KeyValue;
import com.hl.ik.entity.TFIDF_words;
import com.hl.tools.CollectionHelper;


/**
 * 使用IKAnalyzer进行分词的演示
 * 2012-10-22
 *
 */
public class IKAnalzyerTFIDF {
	
	
	public static ArrayList<String> ikWrods(String txt){
		ArrayList<String> lst_words=new ArrayList<String>();
		StringReader sr=new StringReader(txt);  
        IKSegmenter ik=new IKSegmenter(sr, true);  
        Lexeme lex=null;  
        try {
			while((lex=ik.next())!=null){  
				lst_words.add(lex.getLexemeText());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst_words;
	}

	
	public static HashMap<String, Integer> normalTF(String txt){
		ArrayList<String> cutwords=ikWrods(txt);
		
		HashMap<String, Integer> resTF = new HashMap<String, Integer>();
        
        for(String word : cutwords){
            if(resTF.get(word) == null){
                resTF.put(word, 1);
                System.out.println(word);
            }
            else{
                resTF.put(word, resTF.get(word) + 1);
                System.out.println(word.toString());
            }
        }
        
        return (HashMap<String, Integer>)CollectionHelper.sortMapByValues(resTF);
    }
	
	public static HashMap<String, Integer> normalTF(ArrayList<String> cutwords){

		
		HashMap<String, Integer> resTF = new HashMap<String, Integer>();
        
        for(String word : cutwords){
            if(resTF.get(word) == null){
                resTF.put(word, 1);
                System.out.println(word);
            }
            else{
                resTF.put(word, resTF.get(word) + 1);
                System.out.println(word.toString());
            }
        }
        
        return (HashMap<String, Integer>)CollectionHelper.sortMapByValues(resTF);
    }
	
	public static List<TFIDF_words> tf(String txt){
		List<TFIDF_words> arr_tfidfwords=new ArrayList<TFIDF_words>();
		ArrayList<String> cutwords=ikWrods(txt);
        HashMap<String, Float> resTF = new HashMap<String, Float>();
        
        int wordLen = cutwords.size();
        HashMap<String, Integer> intTF = normalTF(txt); 
        
        Iterator iter = intTF.entrySet().iterator(); //iterator for that get from TF
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            TFIDF_words item=new TFIDF_words();
            item.setWord(entry.getKey().toString());
            item.setNums(Integer.parseInt(entry.getValue().toString()));
            item.setTf(Float.parseFloat(entry.getValue().toString()) / wordLen);
            arr_tfidfwords.add(item);
        }
        return arr_tfidfwords;
    } 
	
	public static HashMap<String, Float> tf(ArrayList<String> cutwords){
		HashMap<String, Float> resTF = new HashMap<String, Float>();
        
        int wordLen = cutwords.size();
        HashMap<String, Integer> intTF = normalTF(cutwords); 
        
        Iterator iter = intTF.entrySet().iterator(); //iterator for that get from TF
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            resTF.put(entry.getKey().toString(), Float.parseFloat(entry.getValue().toString()) / wordLen);
            System.out.println(entry.getKey().toString() + " = "+  Float.parseFloat(entry.getValue().toString()) / wordLen);
        }
        return resTF;
    } 
	
	public static HashMap<String, HashMap<String, Float>> tfAllDatas(List<Map> filelist,String fieldName){
		HashMap<String, HashMap<String, Float>> allTF = new HashMap<String, HashMap<String, Float>>();

        
        for (int i = 0; i < filelist.size(); i++) {
        	HashMap<String, Float> dict = new HashMap<String, Float>();
            ArrayList<String> cutwords = ikWrods((String)filelist.get(i).get(fieldName)); //get cut words for one file
            
            dict = tf(cutwords);
            allTF.put(filelist.get(i).get("id").toString(), dict);
		}
        return allTF;
	}
	
	
	public static HashMap<String, Float> idf(HashMap<String,HashMap<String, Float>> all_tf,List<Map> FileList){
        HashMap<String, Float> resIdf = new HashMap<String, Float>();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        int docNum = FileList.size();
        
        for(int i = 0; i < docNum; i++){
        	String id=FileList.get(i).get("id").toString();
            HashMap<String, Float> temp = all_tf.get(id);
            Iterator iter = temp.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String word = entry.getKey().toString();
                if(dict.get(word) == null){
                    dict.put(word, 1);
                }else {
                    dict.put(word, dict.get(word) + 1);
                }
            }
        }
        System.out.println("IDF for every word is:");
        Iterator iter_dict = dict.entrySet().iterator();
        while(iter_dict.hasNext()){
            Map.Entry entry = (Map.Entry)iter_dict.next();
            float value = (float)Math.log(docNum / Float.parseFloat(entry.getValue().toString()));
            resIdf.put(entry.getKey().toString(), value);
            System.out.println(entry.getKey().toString() + " = " + value);
        }
        return resIdf;
    }
    public static List<Map> tf_idf(HashMap<String,HashMap<String, Float>> all_tf,HashMap<String, Float> idfs,List<Map> FileList,float weight){
        HashMap<String, HashMap<String, Float>> resTfIdf = new HashMap<String, HashMap<String, Float>>();
            
        int docNum = FileList.size();
        for(int i = 0; i < docNum; i++){
        	String id=FileList.get(i).get("id").toString();
            HashMap<String, Float> tfidf = new HashMap<String, Float>();
            HashMap<String, Float> temp = all_tf.get(id);
            Iterator iter = temp.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String word = entry.getKey().toString();
                Float value = (float)Float.parseFloat(entry.getValue().toString()) * idfs.get(word); 
                value=value* weight;
                tfidf.put(word, value);
            }
            resTfIdf.put(id, tfidf);
        }
        return DisTfIdf(resTfIdf);
    }
    public static List<Map> DisTfIdf(HashMap<String, HashMap<String, Float>> tfidf){
        Iterator iter1 = tfidf.entrySet().iterator();
        List<Map> list=new ArrayList<Map>();
        while(iter1.hasNext()){
            Map.Entry entrys = (Map.Entry)iter1.next();
            System.out.println("FileName: " + entrys.getKey().toString());
            System.out.print("{");
            
            HashMap<String, Float> temp = (HashMap<String, Float>) entrys.getValue();
            Map<String, Float> linkedtemp=CollectionHelper.sortMapByValuesFloat(temp);
            
            String f_temp="";
            int top10=1;
            
            Iterator iter2 = linkedtemp.entrySet().iterator();
            while(iter2.hasNext()){
            	
                Map.Entry entry = (Map.Entry)iter2.next(); 
                System.out.print(entry.getKey().toString() + " = " + entry.getValue().toString() + ", ");
                
                if(top10<11){
                	f_temp+=entry.getKey().toString() + " = " + entry.getValue().toString() + ", ";
                }
                top10++;
                
                
            }
            System.out.println("}");
            
            Map tempModel=new HashMap<String,Integer>();
            tempModel.put("abs_id", Integer.parseInt(entrys.getKey().toString()));
            tempModel.put("abs_keyword", f_temp);
            list.add(tempModel);
        }
        return list;
    }
    
    
    public static List<Map> totalList(List<Map> lstTitle,List<Map> lstKW,List<Map> lstAbs){
    	List<Map> back=new ArrayList<Map>();
    	try {
    		for (int i = 0; i < lstTitle.size(); i++) {
    			Map item=new HashMap<String, String>();
        		System.out.println(i);
        		if(i==91){
        			System.out.println(i);
        		}
    			Map item_title = (Map) lstTitle.get(i);
    			Map item_kw = (Map) lstKW.get(i);
    			Map item_abs = (Map) lstAbs.get(i);
    			
    			String valTitle=item_title.get("abs_keyword").toString();
    			String valKW=item_kw.get("abs_keyword").toString();
    			String valAbs=item_abs.get("abs_keyword").toString();
    			
    			HashMap<String, Float> mapall=new HashMap<String, Float>();
    			
    			if(!"".equals(valAbs)){
    				String[] arrs=valAbs.split(",");
    				for (int j = 0; j < arrs.length; j++) {
    					if(!"".equals(arrs[j].trim()) && arrs[j].indexOf('=')>-1){
    						String map_key=arrs[j].split("=")[0].trim();
    						String map_value=arrs[j].split("=")[1].trim();
    						mapall.put(map_key, Float.valueOf(map_value));
    					}
    				}
    			}
    			
    			if(!"".equals(valKW)){
    				String[] arrs=valKW.split(",");
    				for (int j = 0; j < arrs.length; j++) {
    					if(!"".equals(arrs[j].trim()) && arrs[j].indexOf('=')>-1){
    						String map_key=arrs[j].split("=")[0].trim();
    						String map_value=arrs[j].split("=")[1].trim();
    						mapall.put(map_key, Float.valueOf(map_value));
    					}
    				}
    			}

    			if(!"".equals(valTitle)){
    				String[] arrs=valTitle.split(",");
    				for (int j = 0; j < arrs.length; j++) {
    					if(!"".equals(arrs[j].trim()) && arrs[j].indexOf('=')>-1){
    						String map_key=arrs[j].split("=")[0].trim();
    						String map_value=arrs[j].split("=")[1].trim();
    						mapall.put(map_key, Float.valueOf(map_value));
    					}
    				}
    			}
    			
    			Map<String, Float> linkedtemp=CollectionHelper.sortMapByValuesFloat(mapall);
    			
    			String f_temp="";
                int top10=1;
                
                Iterator iter2 = linkedtemp.entrySet().iterator();
                while(iter2.hasNext()){
                	
                    Map.Entry entry = (Map.Entry)iter2.next(); 
                    //System.out.print(entry.getKey().toString() + " = " + entry.getValue().toString() + ", ");
                    
                    if(top10<11){
                    	f_temp+=entry.getKey().toString() + " = " + entry.getValue().toString() + ", ";
                    }
                    top10++;
                    
                    
                }

                

                item.put("abs_keyword", f_temp);
    			
    			item.put("abs_id", item_title.get("abs_id"));
    			back.add(item);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

    	return back;
    }
}
