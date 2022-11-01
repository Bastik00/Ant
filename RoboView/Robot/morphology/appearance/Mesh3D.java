package de.hska.lat.robot.morphology.appearance;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.morphology.appearance.loadAppearance.LoadAppearanceData;
import de.hska.lat.robot.morphology.model.Bone;

public class Mesh3D
{
	protected Bone bone;

	protected float[] vertexes;
	protected char[] elements;

	protected int vertexBufferId;
	protected int elementsBufferId;

	protected FloatBuffer vertexBuffer;
	protected CharBuffer elementsBuffer;

	protected int primitiveType;

	private final static int CHAR_SIZE = 2;
	private static final int FLOAT_SIZE = 4;
	private static final int MESH_VERTEX_SIZE = 10 * FLOAT_SIZE;
	private static final int MESH_VERTEX_NORMALS_BUFFER_OFFSET = 3;
	private static final int MESH_VERTEX_COLORS_BUFFER_OFFSET = 6;

	public Mesh3D(Bone bone, float[] vertexes, char[] elements)
	{
		this.bone = bone;
		this.vertexes = vertexes;
		this.elements = elements;

		if (bone != null)
		{
			String fileName = super.getClass().getSimpleName() + "_Bone_"
					+ bone.getId();
			String filePath = "config/" + fileName + ".xml";

			if (new File(filePath).isFile())
			{
				loadAppearanceData(filePath);
			}
		}

		this.vertexBuffer = ByteBuffer
				.allocateDirect(this.vertexes.length * FLOAT_SIZE)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		this.vertexBuffer.put(this.vertexes);

		this.elementsBuffer = ByteBuffer
				.allocateDirect(this.elements.length * CHAR_SIZE)
				.order(ByteOrder.nativeOrder()).asCharBuffer();
		this.elementsBuffer.put(this.elements);

		this.primitiveType = GL2.GL_TRIANGLES;
	}

	private void loadAppearanceData(String filePath)
	{
		LoadAppearanceData loader = new LoadAppearanceData(filePath);
		this.vertexes = loader.getVertices();
		this.elements = loader.getElements();
	}

	public void initMesh(GL gl)
	{
		int[] id = new int[2];

		gl.glGenBuffers(2, id, 0);

		vertexBufferId = id[0];
		elementsBufferId = id[1];

		// Upload the vertex data
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vertexBufferId);
		vertexBuffer.position(0);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertexBuffer.capacity()
				* FLOAT_SIZE, vertexBuffer, GL2.GL_STATIC_DRAW); // GL_STATIC_DRAW

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, elementsBufferId);
		elementsBuffer.position(0);
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, elementsBuffer.capacity()
				* CHAR_SIZE, elementsBuffer, GL2.GL_STATIC_DRAW);

	}

	@SuppressWarnings("static-access")
	public void drawMeshForEditor(GL2 gl)
	{

		//
		int basicPrimitive = this.primitiveType;
		this.primitiveType = gl.GL_TRIANGLE_STRIP;

		FloatVector3D position;
		AngularVector3D heading;
		gl.glClear( GL.GL_DEPTH_BUFFER_BIT);
//GL.GL_COLOR_BUFFER_BIT |
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthMask(true);

		// if (bone==null)
		// return;

		gl.glPushMatrix();
		gl.glEnable(basicPrimitive);
		if (this.bone != null)
		{
			heading = this.bone.getHeading();

			position = bone.getPosition();

			gl.glTranslatef(position.x, position.y, position.z);

			gl.glRotatef(Radiant.convertRadiantToDegree(heading.z), 0.0f, 0.0f,
					1.0f);
			gl.glRotatef(Radiant.convertRadiantToDegree(heading.x), 1.0f, 0.0f,
					0.0f);
			gl.glRotatef(Radiant.convertRadiantToDegree(heading.y), 0.0f, 1.0f,
					0.0f);

		}

		checkGLError(gl);

		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vertexBufferId);

		gl.glVertexPointer(3, GL2.GL_FLOAT, MESH_VERTEX_SIZE, 0);

		gl.glColorPointer(4, GL2.GL_FLOAT, MESH_VERTEX_SIZE,
				MESH_VERTEX_COLORS_BUFFER_OFFSET * FLOAT_SIZE);

		gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
		gl.glNormalPointer(GL2.GL_FLOAT, MESH_VERTEX_SIZE,
				MESH_VERTEX_NORMALS_BUFFER_OFFSET * FLOAT_SIZE);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, elementsBufferId);
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glDrawElements(this.primitiveType, elementsBuffer.length(),
				GL2.GL_UNSIGNED_SHORT, 0);

		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, 0);
		checkGLError(gl);

		gl.glPopMatrix();
		this.primitiveType = basicPrimitive;
	}

	public void drawMesh(GL2 gl)
	{

		FloatVector3D position;
		AngularVector3D heading;

		// if (bone==null)
		// return;

		gl.glPushMatrix();

		if (this.bone != null)
		{
			heading = this.bone.getHeading();

			position = bone.getPosition();

			gl.glTranslatef(position.x, position.y, position.z);

			gl.glRotatef(Radiant.convertRadiantToDegree(heading.z), 0.0f, 0.0f,
					1.0f);
			gl.glRotatef(Radiant.convertRadiantToDegree(heading.x), 1.0f, 0.0f,
					0.0f);
			gl.glRotatef(Radiant.convertRadiantToDegree(heading.y), 0.0f, 1.0f,
					0.0f);

		}

		checkGLError(gl);

		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vertexBufferId);

		gl.glVertexPointer(3, GL2.GL_FLOAT, MESH_VERTEX_SIZE, 0);

		gl.glColorPointer(4, GL2.GL_FLOAT, MESH_VERTEX_SIZE,
				MESH_VERTEX_COLORS_BUFFER_OFFSET * FLOAT_SIZE);

		gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
		gl.glNormalPointer(GL2.GL_FLOAT, MESH_VERTEX_SIZE,
				MESH_VERTEX_NORMALS_BUFFER_OFFSET * FLOAT_SIZE);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, elementsBufferId);

		gl.glDrawElements(this.primitiveType, elementsBuffer.length(),
				GL2.GL_UNSIGNED_SHORT, 0);

		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, 0);
		checkGLError(gl);

		gl.glPopMatrix();

	}

	static void checkGLError(GL gl)
	{
	//	int error = gl.glGetError();

		// if (error != GL2.GL_NO_ERROR)
		{
			// throw new RuntimeException("GLError 0x"
			// + Integer.toHexString(error));
		}
	}

	public float[] getVertexes()
	{
		return vertexes;
	}

	public char[] getElements()
	{
		return elements;
	}

	public Bone getBone()
	{
		return bone;
	}

}
