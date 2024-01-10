import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class JuliaSetProgram extends JPanel implements AdjustmentListener, MouseListener
{
	JFrame frame;
	float aVal, bVal, s1Val, s2Val, b1Val, b2Val, hue1, hue2;
	JScrollBar aBar, bBar, s1Bar, s2Bar, b1Bar, b2Bar, h1Bar, h2Bar;
	JPanel scrollPanel, labelPanel, bigPanel;
	JLabel aLabel, bLabel, s1Label, s2Label, b1Label, b2Label, h1Label, h2Label;
	BufferedImage juliaImage;
	double zoom = 1;
	float maxIter = 300;
	int pixelCount = 1;
	public JuliaSetProgram()
	{
		scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(8, 1));
		frame = new JFrame("Julia Set Program");
		frame.setSize(1300, 800);
		frame.add(this);

		aBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		aBar.setBackground(Color.WHITE);
		aBar.addAdjustmentListener(this);
		aBar.addMouseListener(this);
		scrollPanel.add(aBar);

		bBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		bBar.setBackground(Color.WHITE);
		bBar.addAdjustmentListener(this);
		bBar.addMouseListener(this);
		scrollPanel.add(bBar);

		s1Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		s1Bar.setBackground(Color.WHITE);
		s1Bar.addAdjustmentListener(this);
		s1Bar.addMouseListener(this);
		scrollPanel.add(s1Bar);

		s2Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		s2Bar.setBackground(Color.WHITE);
		s2Bar.addAdjustmentListener(this);
		s2Bar.addMouseListener(this);
		scrollPanel.add(s2Bar);

		b1Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		b1Bar.setBackground(Color.WHITE);
		b1Bar.addAdjustmentListener(this);
		b1Bar.addMouseListener(this);
		scrollPanel.add(b1Bar);

		b2Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		b2Bar.setBackground(Color.WHITE);
		b2Bar.addAdjustmentListener(this);
		b2Bar.addMouseListener(this);
		scrollPanel.add(b2Bar);

		h1Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		h1Bar.setBackground(Color.WHITE);
		h1Bar.addAdjustmentListener(this);
		h1Bar.addMouseListener(this);
		scrollPanel.add(h1Bar);

		h2Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		h2Bar.setBackground(Color.WHITE);
		h2Bar.addAdjustmentListener(this);
		h2Bar.addMouseListener(this);
		scrollPanel.add(h2Bar);


		frame.add(scrollPanel,BorderLayout.SOUTH);

		aVal = aBar.getValue()/1000.0f;
		bVal = bBar.getValue()/1000.0f;
		s1Val = s1Bar.getValue()/1000.0f;
		s2Val = s2Bar.getValue()/1000.0f;
		b1Val = b1Bar.getValue()/1000.0f;
		b2Val = b2Bar.getValue()/1000.0f;
		hue1 = b1Bar.getValue()/1000.0f;
		hue2 = b2Bar.getValue()/1000.0f;

		aLabel = new JLabel("A: "+aVal);
		aLabel.setForeground(Color.BLACK);
		bLabel = new JLabel("B: "+bVal);
		bLabel.setForeground(Color.BLACK);
		s1Label = new JLabel("Background Saturation: "+s1Val);
		s1Label.setForeground(Color.BLACK);
		s2Label = new JLabel("Eye Saturation: "+s2Val);
		s2Label.setForeground(Color.BLACK);

		b1Label = new JLabel("Background Brightness: "+b1Val);
		b1Label.setForeground(Color.BLACK);
		b2Label = new JLabel("Eye Brightness: "+b2Val);
		b2Label.setForeground(Color.BLACK);

		h1Label = new JLabel("Background Hue: "+hue1);
		h1Label.setForeground(Color.BLACK);
		h2Label = new JLabel("Eye Hue: "+hue2);
		h2Label.setForeground(Color.BLACK);

		labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(8,1));
		labelPanel.add(aLabel);
		labelPanel.add(bLabel);
		labelPanel.add(s1Label);
		labelPanel.add(s2Label);
		labelPanel.add(b1Label);
		labelPanel.add(b2Label);
		labelPanel.add(h1Label);
		labelPanel.add(h2Label);

		bigPanel = new JPanel();
		bigPanel.setLayout(new BorderLayout());
		bigPanel.add(labelPanel);
		bigPanel.add(scrollPanel);
		frame.add(bigPanel, BorderLayout.SOUTH);
		bigPanel.add(labelPanel, BorderLayout.WEST);
		bigPanel.add(scrollPanel, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(drawJulia(), 0, 0, null);
	}

	public BufferedImage drawJulia()
	{
		int width = frame.getWidth();
		int height = frame.getHeight();

		juliaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for(int w=0; w<width; w+=pixelCount)
		{
			for(int h=0; h<height; h+=pixelCount)
			{
				float iter=maxIter;
				double zx = 1.5*((w-width*.5)/(.5*zoom*width));
				double zy = (h-height*.5)/(.5*zoom*height);
				while((zx*zx + zy*zy <6) && iter >0)
				{
					double diff = zx*zx - zy*zy + aVal;
					zy = 2*zx*zy + bVal;
					zx = diff;
					iter--;
				}
				int c;
				if(iter>0)
					c = Color.HSBtoRGB(hue1*(float)1.0*iter/maxIter, s1Val, b1Val);
				else
					c = Color.HSBtoRGB(hue2, s2Val, b2Val);
				juliaImage.setRGB(w, h, c);
			}
		}
		return juliaImage;
	}

	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource() == aBar)
		{
			aVal = aBar.getValue()/1000.0f;
			aLabel.setText("A: "+aVal);
		}
		if(e.getSource() == bBar)
		{
			bVal = bBar.getValue()/1000.0f;
			bLabel.setText("B: "+bVal);
		}
		if(e.getSource() == s1Bar)
		{
			s1Val = s1Bar.getValue()/1000.0f;
			s1Label.setText("Background Saturation: "+s1Val);
		}
		if(e.getSource() == s2Bar)
		{
			s2Val = s2Bar.getValue()/1000.0f;
			s2Label.setText("Eye Saturation: "+s2Val);
		}
		if(e.getSource() == b1Bar)
		{
			b1Val = b1Bar.getValue()/1000.0f;
			b1Label.setText("Background Brightness: "+b1Val);
		}
		if(e.getSource() == b2Bar)
		{
			b2Val = b2Bar.getValue()/1000.0f;
			b2Label.setText("Eye Brightness: "+b2Val);
		}

		if(e.getSource() == h1Bar)
		{
			hue1 = h1Bar.getValue()/1000.0f;
			h1Label.setText("Background Hue: "+hue1);
		}
		if(e.getSource() == h2Bar)
		{
			hue2 = h2Bar.getValue()/1000.0f;
			h2Label.setText("Eye Hue: "+hue2);
		}

		repaint();
	}

	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){pixelCount = 3;}
	public void mouseReleased(MouseEvent e){pixelCount = 1;}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	public static void main(String[] args)
	{
		JuliaSetProgram app = new JuliaSetProgram();
	}
}