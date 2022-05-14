package br.edu.unicesumar.prova;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.unicesumar.prova.domain.Aluno;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class AlunoControllerIntTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void criarUmaAlunoTest() throws Exception{
		
		Aluno aluno = new Aluno(null, "123", "Teste", LocalDate.of(2022, 1, 1), new ArrayList<>());
		
		String josonAluno = objectMapper.writeValueAsString(aluno);
		
		MvcResult andReturn = mockMvc.perform(post("/aluno").contentType(MediaType.APPLICATION_JSON)
				.content(josonAluno))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		
		String corpoDaResposta = andReturn.getResponse().getContentAsString();
		
		Aluno alunoResposta = objectMapper.readValue(corpoDaResposta, Aluno.class);
		
		assertNotNull(alunoResposta.getId());
		
		MvcResult andRetur2 = mockMvc.perform(get("/aluno/" + alunoResposta.getId()))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		
		
		MvcResult andReturn3 = mockMvc.perform(put("/aluno/" + alunoResposta.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(corpoDaResposta))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		
		
		MvcResult andRetur4 = mockMvc.perform(delete("/aluno/" + alunoResposta.getId()))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		
	}
		
}
