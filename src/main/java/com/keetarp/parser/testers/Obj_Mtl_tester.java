package com.keetarp.parser.testers;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.keetarp.parser.parser.TexLess_Parser;
import com.keetarp.parser.parser.Tex_Parser;

import javax.swing.*;
import java.awt.*;

public class Obj_Mtl_tester implements GLEventListener {

    public static DisplayMode dm, dm_old;
    private final GLU glu = new GLU();
    private float xrot, yrot, zrot;

    private Tex_Parser objmtlObject;
    private TexLess_Parser objObject;

    public void display(GLAutoDrawable drawable) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(0f, 0f, -5.0f);

        gl.glRotatef(xrot, 1.0f, 1.0f, 1.0f);
        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);

        gl.glFrontFace(GL2.GL_CCW); // Front face in counter-clockwise
        // orientation
        gl.glEnable(GL2.GL_CULL_FACE); // Enable cull face
        gl.glCullFace(GL2.GL_BACK); // Cull the back face (don't display)

        //objmtlObject.draw(gl);
        objObject.draw(gl);

        // change the speeds here
        xrot += .1f;
        yrot += .1f;
        zrot += .1f;
    }

    public void dispose(GLAutoDrawable drawable) {
        // method body
    }

    public void init(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        /**
         * Parsing
         */
        //objmtlObject = new ObjMtl_Parser(gl, "sample", 100);
        objObject = new TexLess_Parser("sample", 100);

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_3D_COLOR_TEXTURE, GL2.GL_NICEST);

        //
        // gl.glEnable(GL2.GL_TEXTURE_2D);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void main(String[] args) {
        /**
         * Parser Ready
         */

        // TODO Auto-generated method stub
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Obj_Mtl_tester r = new Obj_Mtl_tester();

        glcanvas.addGLEventListener(r);
        glcanvas.setSize(400, 400);

        final JFrame frame = new JFrame(" Textured Cube");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        animator.start();
    }

}