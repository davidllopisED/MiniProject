package spdvi;

import POJO.Pictures;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import POJO.Spaces;
import POJO.Users;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * @author bryan
 */
public class SpaceFrame extends javax.swing.JFrame implements Runnable {
    public static Users actualUser = new Users();
    JList<String> lstSpacesName = new JList<>();
    DataAccess da = new DataAccess();
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;
    Properties properties = new Properties();
    private Thread downloadThread;
    
    public SpaceFrame(Users actualUser) {
        initComponents();
        setResizable(false);
        try {
            properties.load(SpaceFrame.class.getClassLoader().getResourceAsStream("BlobService.properties"));
            blobServiceClient = new BlobServiceClientBuilder().connectionString(properties.getProperty("connection")).buildClient();
            containerClient = blobServiceClient.getBlobContainerClient(properties.getProperty("containerName"));
        } catch (IOException ex) {
            Logger.getLogger(SpaceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        scpSpaces.setViewportView(lstSpacesName);
        this.actualUser = actualUser;
        
        if(actualUser.isAdmin() == false) {
            btnInsert.setEnabled(false);
            btnEditar.setEnabled(false);
            btnBorrar.setEnabled(false);
        }
        
        lstSpacesName.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstSpacesNameValueChanged(evt);
            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        panSpace = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        lblSpaceName = new javax.swing.JLabel();
        cboImagen = new javax.swing.JComboBox<>();
        scpDescription = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        lblDescription = new javax.swing.JLabel();
        lblMunicipio = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblWeb = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblEmailData = new javax.swing.JLabel();
        lblPhoneData = new javax.swing.JLabel();
        lblWebData = new javax.swing.JLabel();
        lblDirection = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        lblModalidad = new javax.swing.JLabel();
        lblService = new javax.swing.JLabel();
        txtDirection = new javax.swing.JTextField();
        txtModalidad = new javax.swing.JTextField();
        txtService = new javax.swing.JTextField();
        lblGestor = new javax.swing.JLabel();
        txtGestor = new javax.swing.JTextField();
        prgDownloadImage = new javax.swing.JProgressBar();
        btnInsert = new javax.swing.JButton();
        cmbElemento = new javax.swing.JComboBox<>();
        btnEditar = new javax.swing.JButton();
        btnComent = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        scpSpaces = new javax.swing.JScrollPane();
        scpComments = new javax.swing.JScrollPane();
        scpDescription1 = new javax.swing.JScrollPane();
        txaDescription1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniAllSpaces = new javax.swing.JMenuItem();
        mniExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Espacios");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnCuenta.setText("Cuenta");
        btnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentaActionPerformed(evt);
            }
        });

        panSpace.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblSpaceName.setText("Space Name");

        cboImagen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Imagen" }));
        cboImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboImagenActionPerformed(evt);
            }
        });

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        scpDescription.setViewportView(txaDescription);

        lblDescription.setText("Description");

        lblMunicipio.setText("Municipio");

        lblEmail.setText("email");

        lblWeb.setText("web");

        lblPhone.setText("Tel??fono");

        lblDirection.setText("Direccion");

        lblType.setText("Tipo");

        lblModalidad.setText("Modalidad");

        lblService.setText("Servicio");

        lblGestor.setText("Gestor");

        javax.swing.GroupLayout panSpaceLayout = new javax.swing.GroupLayout(panSpace);
        panSpace.setLayout(panSpaceLayout);
        panSpaceLayout.setHorizontalGroup(
            panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSpaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addComponent(lblGestor, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGestor)
                            .addComponent(lblEmailData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblWebData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhoneData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prgDownloadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addGroup(panSpaceLayout.createSequentialGroup()
                                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtService, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMunicipio)
                            .addComponent(txtType, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scpDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                            .addComponent(txtDirection, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panSpaceLayout.createSequentialGroup()
                                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDirection, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSpaceName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblService, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panSpaceLayout.setVerticalGroup(
            panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSpaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addComponent(lblSpaceName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scpDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMunicipio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDirection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblType))
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prgDownloadImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmailData))
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblWeb)
                        .addGap(4, 4, 4)
                        .addComponent(lblWebData))
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblService)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPhoneData))
                    .addGroup(panSpaceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblModalidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGestor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGestor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnInsert.setText("Insertar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        cmbElemento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Municipio", "Direccion", "Tipo", "Servicio", "Modalidad" }));
        cmbElemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbElementoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnComent.setText("Comentar");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        scpSpaces.setMaximumSize(new java.awt.Dimension(23, 23));

        txaDescription1.setColumns(20);
        txaDescription1.setRows(5);
        scpDescription1.setViewportView(txaDescription1);

        jMenu1.setText("File");

        mniAllSpaces.setText("All Spaces");
        mniAllSpaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAllSpacesActionPerformed(evt);
            }
        });
        jMenu1.add(mniAllSpaces);

        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        jMenu1.add(mniExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbElemento, 0, 165, Short.MAX_VALUE)
                    .addComponent(btnCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(scpSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panSpace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(scpComments, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnComent, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(scpDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch)
                            .addComponent(cmbElemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scpSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInsert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCuenta)
                                .addGap(13, 13, 13))
                            .addComponent(panSpace, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(scpDescription1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scpComments, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        // TODO add your handling code here:
        ProfileDialog profileDialog = new ProfileDialog(this,true);
        profileDialog.setId_registre(actualUser.getId_registre());
        profileDialog.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        SpaceInsertDialog siDialog = new SpaceInsertDialog(this, true); //hacer la ventana insert true
        siDialog.setVisible(true); //visible
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        String selectedSpace = lstSpacesName.getSelectedValue();
        Spaces espacioEditar = new Spaces();
        
        if(selectedSpace != null) {
            
            String[] infoSpace = (selectedSpace.split(", "));
            for (Spaces s: da.getSpaces()) {
                if(s.getNom().equals(infoSpace[0]) && s.getAdreca().equals(infoSpace[1])){
                    espacioEditar = s;
                }
        }   
            
        SpaceEditorDialog seDialog = new SpaceEditorDialog(this, true); //hacer la ventana insert true
        seDialog.setActualSpace(espacioEditar);
        seDialog.setVisible(true); //visible
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        DeleteSpaceDialog deleteDialog = new DeleteSpaceDialog(this, true); //hacer la ventana insert true
        deleteDialog.setVisible(true); //visible
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void mniAllSpacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAllSpacesActionPerformed
        // TODO add your handling code here:
        UpdateSpaceListView();
    }//GEN-LAST:event_mniAllSpacesActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        String search = txtSearch.getText();
        String elemento = cmbElemento.getSelectedItem().toString();
        ArrayList<Spaces> filteredSpaces = new ArrayList<>();
        
        filteredSpaces = da.getSpacesFilter(actualUser.isAdmin(), elemento, search);
        
        for(Spaces s: filteredSpaces) {
            defaultListModel.addElement(s.getNom() + ", " + s.getAdreca());
        }
        
        FlushSpaces();
        lstSpacesName.setModel(defaultListModel); 
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbElementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbElementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbElementoActionPerformed

    private void cboImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboImagenActionPerformed
                                  
        if (cboImagen.getSelectedItem() != null /*|| !(cboImagen.getSelectedItem().toString() == "Imagen")*/) {  //This line prevents double events when selecting by click
//            System.out.println(Thread.currentThread().getName());
            downloadThread = new Thread(this);
            downloadThread.start();
            da.downloadImage(containerClient,prgDownloadImage,cboImagen,lblImage);
                   
        }
       
    }//GEN-LAST:event_cboImagenActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        UpdateSpaceListView();
    }//GEN-LAST:event_formWindowOpened

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        // TODO add your handling code here:
        System.exit(4);
    }//GEN-LAST:event_mniExitActionPerformed
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        da.downloadImage(containerClient,prgDownloadImage,cboImagen,lblImage);
    }
    
   public void UpdateSpaceListView() {
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        
        for (Spaces s: da.getSpaces()) {
        if (!actualUser.isAdmin()){
                if(s.isVisible()){
                defaultListModel.addElement(s.getNom() + ", " + s.getAdreca());
                }
            }
         else {
            defaultListModel.addElement(s.getNom() + ", " + s.getAdreca());
            }
        
        }
        
        lstSpacesName.setModel(defaultListModel);      
    }

   
    private void lstSpacesNameValueChanged(javax.swing.event.ListSelectionEvent evt) {
        String selectedSpace = lstSpacesName.getSelectedValue();
        
        
        if (selectedSpace != null) {
            if (!evt.getValueIsAdjusting()) {  //This line prevents double events when selecting by click
            downloadThread = new Thread(this);
            downloadThread.start();
            lblImage.setText("");
            lblImage.setIcon(new ImageIcon(getClass().getResource("/gif/spinner.gif"))); //ESTA COMENTADA POR QUE ME DA ERROR
        }
            String[] infoSpace = (selectedSpace.split(", "));
            for (Spaces s: da.getSpaces()) {
                if(s.getNom().equals(infoSpace[0]) && s.getAdreca().equals(infoSpace[1])){
                    lblSpaceName.setText(s.getNom());
                    txaDescription.setText(s.getDescripcions());
                    txtMunicipio.setText(s.getMunicipi());
                    txtDirection.setText(s.getAdreca());
                    txtType.setText(s.getTipus());
                    txtService.setText(s.getServeis());
                    txtModalidad.setText(s.getModalitats());
                    txtGestor.setText(s.getGestor());
                    lblEmailData.setText(s.getEmail());
                    lblWebData.setText(s.getWeb());
                    lblPhoneData.setText(String.valueOf(s.getTelefon()));
                    try {
                        UpdateImagenComboModel(s);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(SpaceFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }          
        } 
    }
    
    public void FlushSpaces(){
        DefaultComboBoxModel<String> df = new DefaultComboBoxModel<String>();
        df.addElement("Imagen");
        lblImage.setIcon(null);
        lblSpaceName.setText("");
        txaDescription.setText("");
        txtMunicipio.setText("");
        txtDirection.setText("");
        txtType.setText("");
        txtService.setText("");
        txtModalidad.setText("");
        txtGestor.setText("");
        lblEmailData.setText("");
        lblWebData.setText("");
        lblPhoneData.setText("");
        cboImagen.setModel(df);
    }
    
    private void UpdateImagenComboModel(Spaces espacios) throws MalformedURLException {
        
        DefaultComboBoxModel<String> spacesComboBoxModel = new DefaultComboBoxModel<String>();
        for(Pictures p: da.getImages(espacios.getFk_id_registre())) {
            spacesComboBoxModel.addElement(String.valueOf(p.getName()));
        }
        cboImagen.setModel(spacesComboBoxModel);
        
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SpaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpaceFrame(actualUser).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnComent;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboImagen;
    private javax.swing.JComboBox<String> cmbElemento;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDirection;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailData;
    private javax.swing.JLabel lblGestor;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblModalidad;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPhoneData;
    private javax.swing.JLabel lblService;
    private javax.swing.JLabel lblSpaceName;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblWeb;
    private javax.swing.JLabel lblWebData;
    private javax.swing.JMenuItem mniAllSpaces;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JPanel panSpace;
    private javax.swing.JProgressBar prgDownloadImage;
    private javax.swing.JScrollPane scpComments;
    private javax.swing.JScrollPane scpDescription;
    private javax.swing.JScrollPane scpDescription1;
    private javax.swing.JScrollPane scpSpaces;
    private javax.swing.JTextArea txaDescription;
    private javax.swing.JTextArea txaDescription1;
    private javax.swing.JTextField txtDirection;
    private javax.swing.JTextField txtGestor;
    private javax.swing.JTextField txtModalidad;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtService;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
