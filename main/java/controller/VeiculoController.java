package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Quantidade;
import model.Veiculo;

@WebServlet(urlPatterns = "/p1")
public class VeiculoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Veiculo> lista = new ArrayList<>();
	JsonHelper json = new JsonHelper();
	int id = 1;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String placa = req.getParameter("placa");
		String nome = req.getParameter("nome");
		String marca = req.getParameter("marca");
		double valor = Double.parseDouble(req.getParameter("valor"));

		Veiculo obj = new Veiculo(id, placa, nome, marca, valor);
		lista.add(obj);

		try {
			resp.getWriter().println(json.gerarJson(obj));

		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id++;

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idRemove = Integer.parseInt(req.getParameter("id"));

		for (int i = 0; i < lista.size(); i++) {
			Veiculo objId = (Veiculo) lista.get(i);
			if (objId.getId() == idRemove) {
				lista.remove(i);
			}
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String opcao = req.getParameter("opcao");
		String json1;
		Quantidade qtd = new Quantidade(lista.size());
		if (opcao.equals("quantidade")) {
			try {
				resp.getWriter().print(json.gerarJson(qtd));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcao.equals("buscar placa")) {

			String buscPlaca = req.getParameter("placa");

			for (int i = 0; i < lista.size(); i++) {
				Veiculo placaBusc = lista.get(i);

				if (placaBusc.getPlaca().equals(buscPlaca)) {

					try {
						resp.getWriter().println(json.gerarJson(placaBusc));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				}
			}

		} else if (opcao.equals("todos")) {

			try {
				json1 = json.gerarJsonLista(lista);
				resp.getWriter().print(json1);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcao.equals("barato")) {
			Veiculo objMenor = (Veiculo) lista.get(0);
			double menor = objMenor.getValor();
			int iMenor = 0;
			for (int i = 1; i < lista.size(); i++) {
				objMenor = (Veiculo) lista.get(i);
				if (objMenor.getValor() < menor) {
					menor = objMenor.getValor();
					iMenor = i;

				}
			}

			objMenor = (Veiculo) lista.get(iMenor);

			try {
				resp.getWriter().println(json.gerarJson(objMenor));
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcao.equals("caro")) {
			Veiculo objMaior = (Veiculo) lista.get(0);
			double maior = objMaior.getValor();
			int iMaior = 0;
			for (int i = 1; i < lista.size(); i++) {
				objMaior = (Veiculo) lista.get(i);
				if (objMaior.getValor() > maior) {
					maior = objMaior.getValor();
					iMaior = i;
				}
			}

			objMaior = (Veiculo) lista.get(iMaior);
			try {
				resp.getWriter().println(json.gerarJson(objMaior));
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int idEdit = Integer.parseInt(req.getParameter("id"));

		for (int i = 0; i < lista.size(); i++) {
			Veiculo objId = (Veiculo) lista.get(i);

			if (objId.getId() == idEdit) {
				String placa = req.getParameter("placa");
				String nome = req.getParameter("nome");
				String marca = req.getParameter("marca");
				double valor = Double.parseDouble(req.getParameter("valor"));

				objId.setPlaca(placa);
				objId.setNome(nome);
				objId.setMarca(marca);
				objId.setValor(valor);
				break;
			}
		}
	}

}
