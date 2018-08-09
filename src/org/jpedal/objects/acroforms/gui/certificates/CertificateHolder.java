/*
 * ===========================================
 * Java Pdf Extraction Decoding Access Library
 * ===========================================
 *
 * Project Info:  http://www.idrsolutions.com
 * Help section for developers at http://www.idrsolutions.com/java-pdf-library-support/
 *
 * (C) Copyright 1997-2013, IDRsolutions and Contributors.
 *
 * 	This file is part of JPedal
 *
     This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


 *
 * ---------------
 * CertificateHolder.java
 * ---------------
 */

package org.jpedal.objects.acroforms.gui.certificates;

import java.math.BigInteger;

import javax.swing.JDialog;

public class CertificateHolder extends javax.swing.JPanel {

	private static final long serialVersionUID = 4730202124051679659L;
	private Details detailsTab;
	private General generalTab;
	private JDialog frame;

	public void setValues(String name, int version, String hashAlgorithm, String subjectFields, String issuerFields, BigInteger serialNumber,
			String notBefore, String notAfter, String publicKeyDescription, String publicKey, String x509Data, String sha1Digest, String md5Digest) {
		// TODO Auto-generated method stub
		this.generalTab = new General();
		this.detailsTab = new Details();

		this.generalTab.setValues(name, notBefore, notAfter);
		this.detailsTab.setValues(version, hashAlgorithm, subjectFields, issuerFields, serialNumber, notBefore, notAfter, publicKeyDescription,
				publicKey, x509Data, sha1Digest, md5Digest);

		this.jTabbedPane1.addTab("General", this.generalTab);
		this.jTabbedPane1.addTab("Details", this.detailsTab);
	}

	/**
	 * Creates new form CertificateHolder
	 * 
	 * @param dialog
	 */
	public CertificateHolder(JDialog dialog) {
		initComponents();
		this.frame = dialog;
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {

		this.jTabbedPane1 = new javax.swing.JTabbedPane();
		this.jButton1 = new javax.swing.JButton();

		setLayout(null);
		add(this.jTabbedPane1);
		this.jTabbedPane1.setBounds(10, 10, 420, 360);

		this.jButton1.setText("OK");
		this.jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				close(evt);
			}
		});
		add(this.jButton1);
		this.jButton1.setBounds(350, 390, 73, 23);
	}// </editor-fold>//GEN-END:initComponents

	private void close(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_close
		this.frame.setVisible(false);

		// TODO add your handling code here:
	}// GEN-LAST:event_close

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JTabbedPane jTabbedPane1;
	// End of variables declaration//GEN-END:variables

}
