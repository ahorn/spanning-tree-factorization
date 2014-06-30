TARGET=doc

all: ${TARGET}.pdf

${TARGET}.aux: 
	latex ${TARGET}.tex

${TARGET}.bbl: ${TARGET}.bib ${TARGET}.aux
	bibtex ${TARGET}
# repeat for any other unresolved citation references
	@latex ${TARGET}.tex

${TARGET}.pdf: ${TARGET}.bbl ${TARGET}.tex
	pdflatex --shell-escape --synctex=1 ${TARGET}

clean:
	rm -f ${TARGET}.{log,aux,dvi,bbl,blg,toc}

purge: clean
	rm -f ${TARGET}.pdf

PHONY : all clean purge
