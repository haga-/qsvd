import json

file_a80 = open("avaliacoes_80.txt", "r")
file_a95 = open("avaliacoes_95.txt", "r")

lines_80 = file_a80.read()
lines_95 = file_a95.read()

file_a80.close()
file_a95.close()

avaliacoes_80 = json.loads(lines_80)
avaliacoes_95 = json.loads(lines_95)

respostas_corretas_ishihara = [42, 12, 8, 5, 74, 2, 97, 5, 73]

print "avaliacoes_80:"
for avaliador in avaliacoes_80:
	print avaliador["nome"] + (" -> Possui algum grau de daltonismo." if respostas_corretas_ishihara != avaliador["respostas_ishihara"] else "")
	#for avaliacao in avaliador["avaliacoes"]:
		#print "\t"+avaliacao
print str(len(avaliacoes_80)) + " avaliadores."

print "\navaliacoes_95:"
for avaliador in avaliacoes_95:
	#print avaliador["nome"] + ": "
	#for avaliacao in avaliador["avaliacoes"]:
		#print "\t"+avaliacao
	print avaliador["nome"] + (" -> Possui algum grau de daltonismo." if respostas_corretas_ishihara != avaliador["respostas_ishihara"] else "")

print str(len(avaliacoes_95)) + " avaliadores."
