package helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class JsonHelper {
	
	public String gerarJsonLista(List<?> lista) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		StringBuffer json = new StringBuffer("[");

		for (int i = 0; i < lista.size(); i++) {

			json.append(gerarJson(lista.get(i)));
			if (i < lista.size() - 1)
				json.append(",");
		}
		json.append("]");
		return json.toString();

	}
	
	public String gerarJson(Object o) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
			
			Class clazz = o.getClass();
					
			Method[] metodos = clazz.getDeclaredMethods();
			
			StringBuffer json=new StringBuffer("{");
			
			int qtdGetter=0;
			for (int i = 0; i < metodos.length; i++) {
				
				//se tem a palavra get no nome do metodo
				if (metodos[i].getName().indexOf("get")!=-1  ){
					//quantidade de getter
					qtdGetter++;
					
					String propriedade = metodos[i].getName().substring(3);
					
					Object valor =  metodos[i].invoke(o);
				
					json.append(propriedade.toLowerCase());
					json.append(":");
					json.append(valor);
					
					if (qtdGetter  <  metodos.length-qtdGetter )
						json.append(",");
					
				}
			}
			
			json.append("}");
			return json.toString();
			
		}

}
