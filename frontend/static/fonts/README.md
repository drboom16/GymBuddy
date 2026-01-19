# SF Pro Display Font Files

This directory should contain the following SF Pro Display font files:

## Required Font Files:

1. **SFProDisplay-Regular.woff2** (weight: 400)
   - Regular weight font file

2. **SFProDisplay-Regular.woff** (weight: 400, fallback)
   - Regular weight font file (fallback format)

3. **SFProDisplay-Semibold.woff2** (weight: 600)
   - Semibold weight font file

4. **SFProDisplay-Semibold.woff** (weight: 600, fallback)
   - Semibold weight font file (fallback format)

5. **SFProDisplay-Bold.woff2** (weight: 700)
   - Bold weight font file

6. **SFProDisplay-Bold.woff** (weight: 700, fallback)
   - Bold weight font file (fallback format)

## Font Loading Priority:

The @font-face declarations in `styles/design-system.css` will:
1. First try to use the local system font (works on macOS/iOS)
2. Then load from these web font files if local font is not available
3. Fall back to system fonts if web fonts are not found

## File Structure:

```
/backend/src/main/resources/static/
└── fonts/
    ├── SFProDisplay-Regular.woff2
    ├── SFProDisplay-Regular.woff
    ├── SFProDisplay-Semibold.woff2
    ├── SFProDisplay-Semibold.woff
    ├── SFProDisplay-Bold.woff2
    └── SFProDisplay-Bold.woff
```

## Note:

SF Pro Display is Apple's proprietary font. You'll need to obtain the font files and convert them to .woff2 and .woff formats. The font files should be placed directly in this `/fonts/` directory.

