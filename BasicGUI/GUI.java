import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI extends JPanel implements ActionListener {

	JFrame frame;
	GridLayout menuLayout, buttonLayout, bigPanelLayout;
	JMenuBar menuBar;
	JButton north, south, east, west, reset;
	JMenu fontOptionMenu, fontSizeMenu, textColorMenu, textBackgroundColorMenu, buttonOutlineColorMenu;
	JPanel buttonPanel, bigPanel;
    JMenuItem[] fontOptions, fontSizes, textColors, textBackgroundColors, buttonOutlineColors;
    String[] fontNames, backgroundColorNames, textColorNames, outlineColorName;
    JTextArea textArea;
    Font currentFont;
    Font[] fonts;
    int currentFontSize;
    int[] fontsArray, fontSizesArray;
    Color[] borderColorArray, textColorArray, outlineColorArray, textBackgroundColorArray;

    public GUI(){
        frame = new JFrame("GUI");
        frame.setLayout(new BorderLayout());

        menuBar = new JMenuBar();

        fontOptionMenu = new JMenu("Font Option");
        fontSizeMenu = new JMenu("Font Size");
        textColorMenu = new JMenu("Text Color");
        textBackgroundColorMenu = new JMenu("Text Background Color");
        buttonOutlineColorMenu = new JMenu("Button Outline Color");

        textBackgroundColors = new JMenuItem[]{new JMenuItem("BLACK"), new JMenuItem("YELLOW"), new JMenuItem("RANDOM")};
        buttonOutlineColors = new JMenuItem[]{new JMenuItem("NONE"), new JMenuItem("RED"), new JMenuItem("BLUE"), new JMenuItem("RANDOM")};

        menuLayout = new GridLayout(1,6);

        menuBar.add(fontOptionMenu);
        menuBar.add(fontSizeMenu);
        menuBar.add(textColorMenu);
        menuBar.add(textBackgroundColorMenu);
        menuBar.add(buttonOutlineColorMenu);

        currentFont = new Font("TIMES_NEW_ROMAN", Font.PLAIN, 12);

        Random rand = new Random();
        textBackgroundColorArray = new Color[]{Color.BLUE, Color.PINK, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat())};
        outlineColorArray = new Color[]{Color.WHITE, Color.RED, Color.BLUE, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat())};

        fontNames = new String[]{"Times New Roman", "Consolas", "Arial","Georgia"};
        fontOptions = new JMenuItem[fontNames.length];
        fontSizesArray = new int[]{20,30,40};
        backgroundColorNames = new String[]{"BLUE","PINK","Random"};
        outlineColorName = new String[]{"No Color","Red","Blue", "Random"};


        fonts=new Font[fontNames.length];
        for(int i = 0; i < fontOptions.length; i++)
        {
            fonts[i] = new Font(fontNames[i], Font.PLAIN, fontSizesArray[0]);
            fontOptions[i]=new JMenuItem(fontNames[i]);
            fontOptions[i].setFont(fonts[i]);
            fontOptionMenu.add(fontOptions[i]);
            fontOptions[i].addActionListener(this);
        }
        currentFont=fonts[0];
        fontSizes = new JMenuItem[fontSizesArray.length];

        for(int i = 0; i < fontSizes.length; i++)
        {
            fontSizes[i]=new JMenuItem(""+fontSizesArray[i]);
            fontSizeMenu.add(fontSizes[i]);
            Font temp=new Font(currentFont.getName(), Font.PLAIN, fontSizesArray[i]);
            fontSizes[i].setFont(temp);
            fontSizes[i].addActionListener(this);
        }
        textColorArray = new Color[]{Color.RED, Color.GREEN, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat())};
        textColorNames = new String[]{"RED","GREEN","Random"};
        textColors = new JMenuItem[textColorNames.length];

        for(int i = 0; i < textColors.length; i++)
        {
            textColors[i]=new JMenuItem(""+textColorNames[i]);
            textColorMenu.add(textColors[i]);
            Font temp=new Font(currentFont.getName(), Font.PLAIN, currentFont.getSize());
            textColors[i].setFont(temp);
            textColors[i].setForeground(textColorArray[i]);
            textColors[i].addActionListener(this);
        }

        for(int i = 0; i < textBackgroundColors.length; i++)
        {
            textBackgroundColors[i]=new JMenuItem(""+backgroundColorNames[i]);
            textBackgroundColorMenu.add(textBackgroundColors[i]);
            Font temp=new Font(currentFont.getName(), Font.PLAIN, currentFont.getSize());
            textBackgroundColors[i].setFont(temp);
            textBackgroundColors[i].setForeground(textBackgroundColorArray[i]);
            textBackgroundColors[i].addActionListener(this);
        }

        for(int i = 0; i < buttonOutlineColors.length; i++)
        {
            buttonOutlineColors[i]=new JMenuItem(""+outlineColorName[i]);
            buttonOutlineColorMenu.add(buttonOutlineColors[i]);
            Font temp=new Font(currentFont.getName(), Font.PLAIN, currentFont.getSize());
            buttonOutlineColors[i].setFont(temp);
            buttonOutlineColors[i].setForeground(outlineColorArray[i]);
            buttonOutlineColors[i].addActionListener(this);
        }

        reset = new JButton("Reset");
        reset.addActionListener(this);
        menuBar.add(reset);

        north = new JButton("North");
        east = new JButton("East");
        south = new JButton("South");
        west = new JButton("West");

        bigPanel = new JPanel();
        bigPanelLayout = new GridLayout(1,2);
        bigPanel.setLayout(bigPanelLayout);

        buttonPanel = new JPanel();
        buttonLayout = new GridLayout(1, 4);
        buttonPanel.setLayout(buttonLayout);

        buttonPanel.add(north);
        buttonPanel.add(east);
        buttonPanel.add(south);
        buttonPanel.add(west);

        north.addActionListener(this);
        east.addActionListener(this);
        south.addActionListener(this);
        west.addActionListener(this);

        textArea = new JTextArea();
        textArea.setBackground(textBackgroundColorArray[0]);
        textArea.setForeground(textColorArray[0]);
        textArea.setFont(currentFont);

        bigPanel.add(buttonPanel);
        bigPanel.add(menuBar);
        frame.add(bigPanel,BorderLayout.NORTH);
        frame.add(textArea,BorderLayout.CENTER);
        frame.setSize(1400,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == north)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigPanelLayout = new GridLayout(1, 2);
            bigPanel.setLayout(bigPanelLayout);
            menuLayout = new GridLayout(1, 6);
			buttonPanel.setLayout(buttonLayout);
			menuBar.setLayout(menuLayout);

            frame.add(bigPanel, BorderLayout.NORTH);
        }
        if (e.getSource() == south)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigPanelLayout = new GridLayout(1, 2);
            bigPanel.setLayout(bigPanelLayout);
            menuLayout = new GridLayout(1, 6);
            buttonPanel.setLayout(buttonLayout);
			menuBar.setLayout(menuLayout);

            frame.add(bigPanel, BorderLayout.SOUTH);
        }
        if (e.getSource() == east)
        {
            frame.remove(bigPanel);
			buttonLayout = new GridLayout(4, 1);
			bigPanelLayout = new GridLayout(2, 1);
			menuLayout = new GridLayout(6, 1);
			bigPanel.setLayout(bigPanelLayout);
			buttonPanel.setLayout(buttonLayout);
			menuBar.setLayout(menuLayout);

            frame.add(bigPanel, BorderLayout.EAST);
        }
        if (e.getSource() == west)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(4, 1);
            bigPanelLayout = new GridLayout(2, 1);
            menuLayout = new GridLayout(6, 1);
            bigPanel.setLayout(bigPanelLayout);
            buttonPanel.setLayout(buttonLayout);
            menuBar.setLayout(menuLayout);

            frame.add(bigPanel, BorderLayout.WEST);
        }

        for(int i=0;i<fontOptions.length;i++)
        {
            if (e.getSource() == fontOptions[i])
            {
                currentFont = new Font(fontNames[i], currentFont.getStyle(), currentFont.getSize());
                textArea.setFont(currentFont);
            }
        }

        for(int i=0;i<fontSizes.length;i++)
        {
            if (e.getSource() == fontSizes[i])
            {
                currentFont = new Font(currentFont.getFontName(), currentFont.getStyle(), fontSizesArray[i]);
                textArea.setFont(currentFont);
            }
        }

        for(int i=0;i<textColors.length;i++)
        {
            if (e.getSource() == textColors[i])
            {
                Color c=textColorArray[i];
                if(i == textColors.length-1)
                {
					Random rand = new Random();
					c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
				}
				textColors[i].setForeground(c);
				textArea.setForeground(c);
            }
        }

        for(int i=0;i<textBackgroundColors.length;i++)
        {
            if (e.getSource() == textBackgroundColors[i])
            {
                textArea.setBackground(textBackgroundColorArray[i]);

                if(i == textBackgroundColors.length-1)
                {
					Random rand = new Random();
					Color c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
					textBackgroundColorArray[i]= c;
					textBackgroundColors[i].setForeground(c);
				}
			}
        }

        for(int i=0;i<buttonOutlineColors.length;i++)
        {
            if (e.getSource() == buttonOutlineColors[i])
            {
                north.setBorder(new LineBorder(outlineColorArray[i]));
                east.setBorder(new LineBorder(outlineColorArray[i]));
                south.setBorder(new LineBorder(outlineColorArray[i]));
                west.setBorder(new LineBorder(outlineColorArray[i]));
                reset.setBorder(new LineBorder(outlineColorArray[i]));
            }
                if(i == buttonOutlineColors.length-1)
                {
					Random rand = new Random();
					Color c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
					outlineColorArray[i]= c;
					buttonOutlineColors[i].setForeground(c);
				}
        }

        if(e.getSource() == reset)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigPanelLayout = new GridLayout(1, 2);
            bigPanel.setLayout(bigPanelLayout);
            menuLayout = new GridLayout(1, 6);
			buttonPanel.setLayout(buttonLayout);
			menuBar.setLayout(menuLayout);
            frame.add(bigPanel, BorderLayout.NORTH);
            textArea.setText("");
        }

        frame.revalidate();
    }

    public Random getRandom()
    {
		return new Random();
	}

        public static void main(String[] args) {

	        GUI app = new GUI();
    }
}