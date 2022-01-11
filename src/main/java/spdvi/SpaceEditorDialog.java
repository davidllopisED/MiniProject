/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package spdvi;

import POJO.Pictures;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import POJO.Spaces;
import com.azure.core.util.Context;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobRange;
import com.azure.storage.blob.models.DownloadRetryOptions;
import com.azure.storage.blob.specialized.BlockBlobClient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author bryan
 */
public class SpaceEditorDialog extends javax.swing.JDialog implements Runnable{
    DataAccess da = new DataAccess();
    boolean visible = false;
    Spaces actualSpace;
    private Thread downloadThread;
    SpaceFrame spaceFrame;
    JFileChooser fileChooser = new JFileChooser();
    File imageFile;
    ArrayList<String> Imagenes = new ArrayList<>();
    ArrayList<File> ImageFile = new ArrayList<>();
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;
    Properties properties = new Properties();
    DefaultComboBoxModel<String> spacesComboBoxModel = new DefaultComboBoxModel<String>();
    //Create a unique name for the container
    /**
     * Creates new form SpaceEditorDialog
     */
    public SpaceEditorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        spaceFrame = (SpaceFrame) this.getParent();
        try {
            properties.load(SpaceFrame.class.getClassLoader().getResourceAsStream("BlobService.properties"));
            blobServiceClient = new BlobServiceClientBuilder().connectionString(properties.getProperty("connection")).buildClient();
            containerClient = blobServiceClient.getBlobContainerClient(properties.getProperty("containerName"));
        } catch (IOException ex) {
            Logger.getLogger(SpaceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lstImages.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstImagesValueChanged(evt);
            }
        });
        
        
    }

    public Spaces getActualSpace() {
        return actualSpace;
    }

    public void setActualSpace(Spaces actualSpace) {
        this.actualSpace = actualSpace;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        txtImagenName = new javax.swing.JTextField();
        scpImagen = new javax.swing.JScrollPane();
        lstImages = new javax.swing.JList<>();
        btnAgregar = new javax.swing.JButton();
        lblSpaceName = new javax.swing.JLabel();
        txtSpaceName = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        scpDescription = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        lblModalidad = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        lblService = new javax.swing.JLabel();
        txtDirection = new javax.swing.JTextField();
        txtModalidad = new javax.swing.JTextField();
        txtService = new javax.swing.JTextField();
        lblDirection = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        lblMunicipio = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblWeb = new javax.swing.JLabel();
        txtWeb = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        chbMostrar = new javax.swing.JCheckBox();
        lblGestor = new javax.swing.JLabel();
        txtGestor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SpaceInsert");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnOpen.setText("Abrir");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        txtImagenName.setName(""); // NOI18N
        txtImagenName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtImagenNameFocusGained(evt);
            }
        });

        lstImages.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstImagesValueChanged(evt);
            }
        });
        scpImagen.setViewportView(lstImages);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        lblSpaceName.setText("Nombre");

        lblDescripcion.setText("Descripción");

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        scpDescription.setViewportView(txaDescription);

        lblModalidad.setText("Modalidad");

        lblService.setText("Servicio");

        lblDirection.setText("Direccion");

        lblType.setText("Tipo");

        lblMunicipio.setText("Municipio");

        lblEmail.setText("Email");

        lblWeb.setText("Web");

        lblPhone.setText("Teléfono");

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAccept.setText("Aceptar");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        btnRemove.setText("Quitar");

        chbMostrar.setText("Mostrar");
        chbMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMostrarActionPerformed(evt);
            }
        });

        lblGestor.setText("Gestor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblWeb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtWeb, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(scpImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addComponent(txtImagenName))
                            .addGap(32, 32, 32)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(lblImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGestor)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSpaceName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtService, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblSpaceName, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addComponent(scpDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addComponent(txtMunicipio)
                                .addComponent(txtDirection, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDirection, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblService, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 293, Short.MAX_VALUE)))
                            .addComponent(lblGestor, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chbMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSpaceName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSpaceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scpDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMunicipio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDirection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOpen)
                            .addComponent(txtImagenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scpImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblWeb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPhone)
                                .addGap(0, 0, 0)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAgregar)
                                    .addComponent(lblType))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRemove))
                                .addGap(18, 18, 18)
                                .addComponent(lblService)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblModalidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblGestor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGestor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAccept)
                                    .addComponent(btnCancel)
                                    .addComponent(chbMostrar))
                                .addGap(25, 25, 25))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        
        /*int returnOption = fileChooser.showOpenDialog(this);
        if (returnOption == JFileChooser.APPROVE_OPTION){
            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
                ImageIcon icon = da.resizeImageIcon(bufferedImage, lblImage.getWidth(), lblImage.getHeight());
                lblImage.setIcon(icon);
            } catch (IOException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            //"\\AppData\\Local\\UserList2\\images\\" 
        }*/
        
        //PARTE DE REVISION
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            BlobClient blobClient = containerClient.getBlobClient(fileChooser.getSelectedFile().getName());
            txtImagenName.setText(fileChooser.getSelectedFile().getName()); //El nombre lo pone
            try {
                BufferedImage bufferedImage = ImageIO.read(fileChooser.getSelectedFile().getAbsoluteFile());
                BufferedImage bufferedImagePath = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath())); //Pero no lo lee como imagen
                ImageIcon icon = da.resizeImageIcon(bufferedImagePath, lblImage.getWidth(), lblImage.getHeight());
                lblImage.setIcon(icon);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                blobClient.upload(bais, baos.size());  // Thread blocking
                BlobHttpHeaders headers = new BlobHttpHeaders();
                headers.setContentType("image/jpeg");
                blobClient.setHttpHeaders(headers);
                baos.close();
                bais.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void txtImagenNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtImagenNameFocusGained
        // TODO add your handling code here:
        txtImagenName.selectAll();
    }//GEN-LAST:event_txtImagenNameFocusGained

    private void lstImagesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstImagesValueChanged
       if (!evt.getValueIsAdjusting()) {  //This line prevents double events when selecting by click
//            System.out.println(Thread.currentThread().getName());
            downloadThread = new Thread(this);
            downloadThread.start();
//            downloadImage();
            lblImage.setText("");
            lblImage.setIcon(new ImageIcon(getClass().getResource("/gif/spinner.gif")));
        }
        String selectedImagen = lstImages.getSelectedValue();
       File imagePath;
        if (selectedImagen != null) {
            for (int i = 0; i < Imagenes.size() ; i++) {
            if (Imagenes.get(i).equals(selectedImagen)) {
                imagePath = ImageFile.get(i);
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(imagePath);
                    ImageIcon icon = da.resizeImageIcon(bufferedImage, lblImage.getWidth(), lblImage.getHeight());
                    lblImage.setIcon(icon);
                } catch (IOException ex) {
                    Logger.getLogger(SpaceInsertDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }               
        }
    }//GEN-LAST:event_lstImagesValueChanged

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        downloadImage();
    }
    
    private void downloadImage() {
        // Downloading big images in chunks of 1kB might be very slow because of the request overhead to azure. Modify the algorithm to donwload eavery image in, for instance 20 chunks.

        ByteArrayOutputStream outputStream;
        BufferedImage originalImage;
        try {
            BlockBlobClient blobClient = containerClient.getBlobClient(lstImages.getSelectedValue()).getBlockBlobClient();
            
            int dataSize = (int) blobClient.getProperties().getBlobSize();
            int numberOfBlocks = 20;
            int numberOfBPerBlock = dataSize / numberOfBlocks;  // Split every image in 20 blocks. That is, make 20 requests to Azure.
            System.out.println("Starting download of " + dataSize + " bytes in " + numberOfBlocks + " " + numberOfBPerBlock/1024 + "kB chunks");

            
            int i = 0;
            outputStream = new ByteArrayOutputStream(dataSize);

            while (i < numberOfBlocks) {
                BlobRange range = new BlobRange(i * numberOfBPerBlock, (long)numberOfBPerBlock);
                DownloadRetryOptions options = new DownloadRetryOptions().setMaxRetryRequests(5);

                System.out.println(i + ": Downloading bytes " + range.getOffset() + " to " + (range.getOffset() + range.getCount()) + " with status "
                        + blobClient.downloadStreamWithResponse(outputStream, range, options, null, false,
                                Duration.ofSeconds(30), Context.NONE));
                i++;
            }

            // Download the last bytes of the image
            BlobRange range = new BlobRange(i * numberOfBPerBlock);
            DownloadRetryOptions options = new DownloadRetryOptions().setMaxRetryRequests(5);
            System.out.println(i + ": Downloading bytes " + range.getOffset() + " to " + dataSize + " with status "
                    + blobClient.downloadStreamWithResponse(outputStream, range, options, null, false,
                            Duration.ofSeconds(30), Context.NONE));
            i++;
            
//            blobClient.downloadStream(outputStream);  // Thread Blocking
            originalImage = ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray()));
            ImageIcon icon = da.resizeImageIcon(originalImage, lblImage.getWidth(), lblImage.getHeight());
            lblImage.setIcon(icon);
            outputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String nombreImagen = fileChooser.getSelectedFile().getName();
        UpdateSpaceListView(nombreImagen);
    }//GEN-LAST:event_btnAgregarActionPerformed

    public void UpdateSpaceListView(String Imagen) {
        spacesComboBoxModel.addElement(Imagen);  
        lstImages.setModel(spacesComboBoxModel);      
    }
    
    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        // TODO add your handling code here:
        //En esta parte se hace el insert de la informacion con respecto a loc campos indicados
        //o en su contrario es posible hacer los inserts en las partes pertinentes quitando el autocommit y en esta parte hacer el commit final
        Spaces newSpace = new Spaces(
                getActualSpace().getFk_id_registre(), 
                txtSpaceName.getText(), 
                txaDescription.getText(),
                txtMunicipio.getText(), 
                txtDirection.getText(),
                txtEmail.getText(), 
                txtWeb.getText(), 
                txtType.getText(),
                txtModalidad.getText(), 
                txtGestor.getText(), 
                txtService.getText(), 
                Integer.parseInt(txtPhone.getText()),
                chbMostrar.isSelected()
        );
        
        
        da.actualizarSpace(newSpace);
        try {

            if (!Imagenes.isEmpty()) {
                for(int i = 0; i < Imagenes.size(); i++) {
                    da.UploadImagen(Imagenes.get(i), ImageFile.get(i), containerClient);
                }
                
                for(String imagen: Imagenes) {
                    Pictures newPicture = new Pictures(da.newRegistre("imatges"), imagen);
                    da.insertImage(newPicture);
                    da.insertRelation(newSpace.getFk_id_registre(), newPicture.getId());
                }
                
            }
            else {
                
                //La imagen default tiene como id 0
                da.insertRelation(newSpace.getFk_id_registre(), 0);

            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            ex.printStackTrace();
        }
        spaceFrame.UpdateSpaceListView();
        this.setVisible(false); 
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        //Si se hace algun insert o alguna accion durante la edicion y no se ha dado aceptar sino cancelar, se puede hacer un rollback y quitar la ventana
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void chbMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbMostrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        txtSpaceName.setText(actualSpace.getNom());
        txaDescription.setText(actualSpace.getDescripcions());
        txtMunicipio.setText(actualSpace.getMunicipi());
        txtDirection.setText(actualSpace.getAdreca());
        txtType.setText(actualSpace.getTipus());
        txtService.setText(actualSpace.getServeis());
        txtModalidad.setText(actualSpace.getModalitats());
        txtGestor.setText(actualSpace.getGestor());
        txtEmail.setText(actualSpace.getEmail());
        txtWeb.setText(actualSpace.getWeb());
        txtPhone.setText(String.valueOf(actualSpace.getTelefon()));
        chbMostrar.setSelected(actualSpace.isVisible());
        try {
            UpdateImagenJList(actualSpace);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SpaceEditorDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void UpdateImagenJList(Spaces actualSpace) throws MalformedURLException {
       
        for(Pictures p: da.getImages(actualSpace)) {
            spacesComboBoxModel.addElement(String.valueOf(p.getName()));
        }
        lstImages.setModel(spacesComboBoxModel); 
    }
    
    
    
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
            java.util.logging.Logger.getLogger(SpaceEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpaceEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpaceEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpaceEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SpaceEditorDialog dialog = new SpaceEditorDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRemove;
    private javax.swing.JCheckBox chbMostrar;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDirection;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGestor;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblModalidad;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblService;
    private javax.swing.JLabel lblSpaceName;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblWeb;
    private javax.swing.JList<String> lstImages;
    private javax.swing.JScrollPane scpDescription;
    private javax.swing.JScrollPane scpImagen;
    private javax.swing.JTextArea txaDescription;
    private javax.swing.JTextField txtDirection;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGestor;
    private javax.swing.JTextField txtImagenName;
    private javax.swing.JTextField txtModalidad;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtService;
    private javax.swing.JTextField txtSpaceName;
    private javax.swing.JTextField txtType;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables
}
