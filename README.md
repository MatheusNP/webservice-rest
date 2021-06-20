# webservice-rest

Exemplo de webservice REST, desenvolvido em Spring Boot.

### REQUEST

Deve-se realizar uma requisição POST, do tipo Multipart Form, para "/upload", enviando os seguintes campos:

- cpf: String
- pdf: File



### RESPONSE

A resposta da requisição deve ser um JSON com o campo "hashResult" e o valor da chave Hash gerada a partir do: CPF, Data e hora da chamada e IP do usuário que realizou a requisição.



### RODAPÉ DE PDF COM HASH 

Será criada uma pasta na raíz do projeto onde será armazenado o PDF enviado - em "/uploads/tmp/" - e o PDF com a hash gerada no rodapé - em "/uploads/". Os arquivos enviados para "/uploads/tmp/" são utilizados apenas para gerar o PDF com a hash, sendo descartados depois do processo.

Foi utilizado a biblioteca iText 7 para manipulação do PDF.
