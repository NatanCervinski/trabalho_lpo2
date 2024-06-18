
package locadora.view;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import locadora.controller.VeiculoController;
import locadora.model.Automovel;
import locadora.model.Categoria;
import locadora.model.Estado;
import locadora.model.Marca;
import locadora.model.ModeloAutomovel;
import locadora.model.ModeloMotocicleta;
import locadora.model.ModeloVan;
import locadora.model.Motocicleta;
import locadora.model.Van;
import locadora.model.Veiculo;


public class CadastroVeiculoView extends javax.swing.JFrame {

    
    private VeiculoController veiculoController;

    public CadastroVeiculoView() {
        initComponents();
        veiculoController = new VeiculoController();
        boxTipoActionPerformed(null);
        campoPlaca();
      //  campoValor();
    }

    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        boxMarca = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        boxEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        boxCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        boxTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelAno = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        boxModelo = new javax.swing.JComboBox<>();
        labelPlaca = new javax.swing.JFormattedTextField();
        labelValorCompra = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Marca:");

        boxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VW", "GM", "Fiat", "Honda", "Mercedes" }));
        boxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxMarcaActionPerformed(evt);
            }
        });

        jLabel2.setText("Estado:");

        boxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOVO", "LOCADO", "DISPONIVEL", "VENDIDO" }));
        boxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxEstadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Categoria:");

        boxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "POPULAR", "INTERMEDIARIO", "LUXO" }));
        boxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCategoriaActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo:");

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Automovel", "Motocicleta", "Van" }));
        boxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoActionPerformed(evt);
            }
        });

        jLabel5.setText("Valor da compra:");

        jLabel6.setText("Placa:");

        jLabel7.setText("Ano:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel8.setText("Modelo:");

        boxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gol", "Celta", "Palio", "Fiesta", "Civic", "Corolla", "CG125", "CBR500", "Ninja300", "XJ6", "Kombi", "Sprinter", "Ducato" }));
        boxModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxModeloActionPerformed(evt);
            }
        });

        labelPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelPlacaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelPlaca, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnSalvar)
                        .addComponent(labelAno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(boxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(labelPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxMarcaActionPerformed

    private void boxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxEstadoActionPerformed

    private void boxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxCategoriaActionPerformed

    private void boxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoActionPerformed
            String selectedTipo = (String) boxTipo.getSelectedItem();
    DefaultComboBoxModel<String> modelOptions = veiculoController.getModelOptions(selectedTipo);
    boxModelo.setModel(modelOptions);
    }//GEN-LAST:event_boxTipoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            String tipo = (String) boxTipo.getSelectedItem();
            Marca marca = Marca.valueOf((String) boxMarca.getSelectedItem());
            Estado estado = Estado.valueOf((String) boxEstado.getSelectedItem());
            Categoria categoria = Categoria.valueOf((String) boxCategoria.getSelectedItem());
            double valorDeCompra = Double.parseDouble(labelValorCompra.getText());
            String placa = labelPlaca.getText();
            int ano = Integer.parseInt(labelAno.getText());
            String modelo = (String) boxModelo.getSelectedItem();

            Veiculo veiculo = null;
            switch (tipo) {
                case "Automovel":
                    veiculo = new Automovel(marca, estado, categoria, valorDeCompra, placa, ano, ModeloAutomovel.valueOf(modelo));
                    break;
                case "Motocicleta":
                    veiculo = new Motocicleta(marca, estado, categoria, valorDeCompra, placa, ano, ModeloMotocicleta.valueOf(modelo));
                    break;
                case "Van":
                    veiculo = new Van(marca, estado, categoria, valorDeCompra, placa, ano, ModeloVan.valueOf(modelo));
                    break;
            }

            veiculoController.addVeiculo(veiculo);
            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void boxModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxModeloActionPerformed

    private void labelPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_labelPlacaActionPerformed
private void campoPlaca(){
        try {
            MaskFormatter mask = new MaskFormatter("UUU-####");
            mask.install(labelPlaca);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "FAVOR INSERIR A PLACA NO FORMATO UUU-####", "ERRO", JOptionPane.ERROR);
        }
}
   /* private void campoValor(){
        try {
            MaskFormatter mask = new MaskFormatter("R$#####,##");
            mask.install(labelValorCompra);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir valor", "ERRO", JOptionPane.ERROR);
        }
} */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroVeiculoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroVeiculoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroVeiculoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroVeiculoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroVeiculoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxCategoria;
    private javax.swing.JComboBox<String> boxEstado;
    private javax.swing.JComboBox<String> boxMarca;
    private javax.swing.JComboBox<String> boxModelo;
    private javax.swing.JComboBox<String> boxTipo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField labelAno;
    private javax.swing.JFormattedTextField labelPlaca;
    private javax.swing.JFormattedTextField labelValorCompra;
    // End of variables declaration//GEN-END:variables
}
