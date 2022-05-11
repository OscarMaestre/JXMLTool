String xmlResultado=ProcesadorXML.nodeListToString(resultados);
                txtInformes.setText(xmlResultado);
            } catch (Exception ex) {
                txtInformes.setText(ex.toStri